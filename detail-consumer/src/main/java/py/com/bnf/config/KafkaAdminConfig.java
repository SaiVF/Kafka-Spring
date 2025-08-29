package py.com.bnf.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@Configuration
public class KafkaAdminConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        var config = new HashMap<String, Object>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new KafkaAdmin(config);
    }

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("lote-detalles-topic")
                        .partitions(2)
                        .replicas(1)
                        .build()
        );
    }
}
