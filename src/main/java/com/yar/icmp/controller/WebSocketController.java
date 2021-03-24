package com.yar.icmp.controller;

import com.yar.icmp.common.RequestMessage;
import com.yar.icmp.common.WebSocketResponse;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/hello")
	public void sendSpecific(RequestMessage message) throws Exception {
		while (true){
			Thread.sleep(1000); // simulated delay
			// request
			final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();
			request.setHost(message.getName());
			// delegate
			final IcmpPingResponse response = IcmpPingUtil.executePingRequest(request);
			// log
			final String formattedResponse = IcmpPingUtil.formatResponse(response);
			System.out.println(formattedResponse);
			simpMessagingTemplate.convertAndSend("/topic/pings", new WebSocketResponse(formattedResponse));
		}
	}

//	@MessageMapping("/hello")
	Flux<WebSocketResponse> stream(RequestMessage message) {
		return Flux
				.interval(Duration.ofSeconds(1))
				.map(index -> {
					// request
					final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();
					request.setHost(message.getName());
					// delegate
					final IcmpPingResponse response = IcmpPingUtil.executePingRequest(request);
					// log
					final String formattedResponse = IcmpPingUtil.formatResponse(response);
					System.out.println(formattedResponse);
					return new WebSocketResponse(formattedResponse);
				})
				.log();
	}

}
