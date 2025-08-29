package py.com.bnf.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DetailProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DetailProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarDetalle(Long pagoLoteId, String detalleId) {
        kafkaTemplate.send("lote-detalles-topic", pagoLoteId.toString(), detalleId)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error al enviar detalle {}: {}", detalleId, ex.getMessage());
                    } else {
                        log.info("Detalle enviado: {} a partición {}, offset {}",
                                detalleId,
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}
