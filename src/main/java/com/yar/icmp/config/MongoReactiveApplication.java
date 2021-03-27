package com.yar.icmp.config;

import java.io.IOException;

import com.mongodb.internal.async.client.AsyncMongoClient;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.internal.MongoClientImpl;
//import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.yar.icmp.repository")
public class MongoReactiveApplication /*extends AbstractReactiveMongoConfiguration*/ {

//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "reactive";
//    }
//
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() {
//        return new ReactiveMongoTemplate(mongoClient(), "icmp");
//    }
}