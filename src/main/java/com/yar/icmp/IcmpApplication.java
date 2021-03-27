package com.yar.icmp;

import com.yar.icmp.domain.HostEntity;
import com.yar.icmp.repository.HostRepository;
import com.yar.icmp.service.PingJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
public class IcmpApplication implements CommandLineRunner {

	public IcmpApplication(HostRepository hostRepository, PingJobService pingJobService) {
		this.hostRepository = hostRepository;
		this.pingJobService = pingJobService;
	}

	public static void main(String[] args) {
		SpringApplication.run(IcmpApplication.class, args);
	}

	private final HostRepository hostRepository;
	private final PingJobService pingJobService;

	@Override
	public void run(String... args) throws Exception {
		hostRepository.save(new HostEntity(null, "8.8.8.8",1000, LocalDateTime.now()));
		hostRepository.save(new HostEntity(null, "127.0.0.1",1000, LocalDateTime.now()));
		hostRepository.save(new HostEntity(null, "dddd",1000, LocalDateTime.now()));
		pingJobService.initiatePingJobs();
	}
}
