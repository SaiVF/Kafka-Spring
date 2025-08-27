package com.svf.bknd.str_consumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

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

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void listener2(String message) {
        log.info("LISTENER3 ::: Recibiendo mensaje {}", message);
    }

}
