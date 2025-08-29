package py.com.bnf.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import py.com.bnf.service.DetailService;

@Component
public class DetailConsumer {

    private final DetailService detailService;

    public DetailConsumer(DetailService detailService) {
        this.detailService = detailService;
    }

    @KafkaListener(topics = "lotes-cabecera-topic", groupId = "spring.kafka.consumer.group-id")
    public void consumerMessage(String pagoLoteId) {
        System.out.println("📥 Recibido pagoLoteId: " + pagoLoteId);
        detailService.obtenerDetallesPorLote(Long.parseLong(pagoLoteId));
    }
}
