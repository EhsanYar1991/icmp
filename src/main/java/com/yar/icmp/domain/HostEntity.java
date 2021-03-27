package com.yar.icmp.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Host")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id","host"})
public class HostEntity {
    @Id
    private String id;

    @Indexed(unique=true)
    private String host;

    private long delayTime = 1000L;

    private LocalDateTime submittedTime = LocalDateTime.now();
}
