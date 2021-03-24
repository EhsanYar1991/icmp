package com.yar.icmp;

import com.yar.icmp.domain.HostEntity;
import com.yar.icmp.repository.HostRepository;
import com.yar.icmp.service.PingJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class IcmpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IcmpApplication.class, args);
	}

	@Autowired
	private HostRepository hostRepository;
	@Autowired
	private PingJobService pingJobService;

	@Override
	public void run(String... args) throws Exception {
		hostRepository.save(new HostEntity(null, "8.8.8.8",1000, LocalDateTime.now()));
		hostRepository.save(new HostEntity(null, "127.0.0.1",1000, LocalDateTime.now()));
		hostRepository.save(new HostEntity(null, "dddd",1000, LocalDateTime.now()));
		pingJobService.initiatePingJobs();
	}
}
