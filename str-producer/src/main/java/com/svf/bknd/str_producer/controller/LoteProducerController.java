package com.svf.bknd.str_producer.controller;

import com.svf.bknd.str_producer.services.LoteProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class LoteProducerController {

    @Autowired
    private LoteProducerService loteProducerService;

    @PostMapping("/sendLoteCabecera")
    public ResponseEntity<?> sendLoteCabecera(@RequestBody String lote) {
        loteProducerService.sendLoteCabecera(lote);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

