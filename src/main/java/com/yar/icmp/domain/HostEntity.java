package com.yar.icmp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id","host"})
public class HostEntity {
    @Id
    private String id;

    private String host;

    private long delayTime = 1000L;

    private LocalDateTime submittedTime = LocalDateTime.now();
}
