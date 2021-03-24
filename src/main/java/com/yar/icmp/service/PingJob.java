package com.yar.icmp.service;

import com.yar.icmp.common.WebSocketResponse;
import com.yar.icmp.domain.HostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PingJob extends Thread {

    private HostEntity host;
    private long delayTime;
    private SimpMessagingTemplate simpMessagingTemplate;


    @Override
    public void run() {
        super.run();
        while (true){
            IcmpPingResponse response;
            try {
                TimeUnit.SECONDS.sleep(1);
//                Thread.currentThread().wait(delayTime > 100 ? delayTime : 1000);
                // request
                final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();
                request.setHost(host.getHost());
                // delegate
                response = IcmpPingUtil.executePingRequest(request);
                // log
                final String formattedResponse = IcmpPingUtil.formatResponse(response);
                System.out.println(formattedResponse);
                simpMessagingTemplate.convertAndSend("/topic/pings", new WebSocketResponse(formattedResponse));
            } catch (Exception e) {
                String message = "Thread: " + getId() + " Host Job :" + host.getHost() + " has been failed.";
                response = new IcmpPingResponse();
                response.setHost(host.getHost());
                response.setOutput(message);
                response.setErrorMessage(e.getMessage());
                log.error(message, e);
                simpMessagingTemplate.convertAndSend("/topic/pings", new WebSocketResponse(IcmpPingUtil.formatResponse(response)));
            }
        }
    }

}
