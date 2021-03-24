package com.yar.icmp.repository;

import com.yar.icmp.domain.HostEntity;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends RxJava2CrudRepository<HostEntity, Long> {

}
