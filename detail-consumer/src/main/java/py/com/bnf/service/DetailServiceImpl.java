package py.com.bnf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import py.com.bnf.producer.DetailProducer;

import java.util.Arrays;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {

    @Value("${external.service.base-url}")
    private String baseUrl;

    @Value("${external.service.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final DetailProducer detailProducer;

    public DetailServiceImpl(DetailProducer detailProducer) {
        this.detailProducer = detailProducer;
    }

    @Override
    public void obtenerDetallesPorLote(Long pagoLoteId) {
        String url = baseUrl + "/pago-lote/listar-id-detalles?pagoLoteId=" + pagoLoteId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RshkMichi-ApiKey", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Long[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Long[].class);
        Long[] detalles = response.getBody();

        List<Long> detalleList = Arrays.asList(detalles != null ? detalles : new Long[0]);
        System.out.println("🔎 Detalles obtenidos: " + detalleList);
        detalleList.forEach(detalleId ->
                detailProducer.enviarDetalle(pagoLoteId, detalleId.toString())
        );
    }
}
