package com.yar.icmp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmEntity {
    @Id
    private String id;

    private HostEntity host;

    private String message;

    private LocalDateTime submittedTime = LocalDateTime.now();

}
