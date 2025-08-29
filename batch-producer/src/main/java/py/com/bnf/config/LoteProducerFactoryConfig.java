package py.com.bnf.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class LoteProducerFactoryConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        configs.put(ProducerConfig.ACKS_CONFIG, "all");       // espera confirmación de todas las réplicas, en caso de usar replicas
        configs.put(ProducerConfig.RETRIES_CONFIG, 3);       // reintentos en fallo
        configs.put(ProducerConfig.LINGER_MS_CONFIG, 0);
//        batching opcional, el productor espera hasta 5 ms para agrupar más mensajes antes de enviarlos.
//        configs.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);// tamaño de batch

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
