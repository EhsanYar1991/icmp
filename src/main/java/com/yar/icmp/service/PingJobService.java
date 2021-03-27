package com.yar.icmp.service;

import com.yar.icmp.domain.HostEntity;
import com.yar.icmp.repository.HostRepository;
import io.reactivex.Flowable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Service
@Data
@Slf4j
public class PingJobService {

    private Map<String, PingJob> pingJobMap ;

    private final HostRepository hostRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ExecutorService executorService;

    public PingJobService(HostRepository hostRepository, SimpMessagingTemplate simpMessagingTemplate, ExecutorService executorService) {
        this.hostRepository = hostRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.executorService = executorService;
    }

    public void initiatePingJobs(){
        if (pingJobMap == null) pingJobMap = new ConcurrentHashMap<>();

        Flowable<HostEntity> hosts = hostRepository.findAll();
        hosts.subscribe(host -> {
            PingJob pingJob = new PingJob();
            pingJob.setSimpMessagingTemplate(simpMessagingTemplate);
            pingJob.setDaemon(true);
            pingJob.setHost(host);
            pingJob.setDelayTime(host.getDelayTime());
            executorService.submit(pingJob);
            pingJobMap.put(host.getHost(), pingJob);
        });
        log.info("Initiated");
    }


}
