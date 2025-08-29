package com.svf.bknd.str_consumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Component
public class StrConsumerListener {

    @KafkaListener(groupId = "group-0",
            topicPartitions = @TopicPartition(topic = "str-topic", partitions = {"0"}),
            containerFactory = "validMessageContainerFactory")
    public void listener0(String message) {
        log.info("LISTENER1 ::: Recibiendo mensaje {}", message);
    }

    @KafkaListener(groupId = "group-0",
            topicPartitions = @TopicPartition(topic = "str-topic", partitions = {"1"}),
            containerFactory = "validMessageContainerFactory")
    public void listener1(String message) {
        log.info("LISTENER2 ::: Recibiendo mensaje {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "lotes-cabecera-topic", containerFactory = "validMessageContainerFactory")
    public void listener2(String message) {
        log.info("LISTENER3 ::: Recibiendo mensaje {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "lotes-cabecera-topic", containerFactory = "validMessageContainerFactory")
    public void listener3(String idLote) {
        log.info("LISTENER4 ::: Recibiendo mensaje de lote {}", idLote);
    }



}
