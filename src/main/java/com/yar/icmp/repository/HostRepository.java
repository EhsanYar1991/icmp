package com.yar.icmp.repository;

import com.yar.icmp.domain.HostEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends RxJava2CrudRepository<HostEntity, String> {

}
