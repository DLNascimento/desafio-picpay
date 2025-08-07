package com.example.desafio_picpay_transacao.controller;

import com.example.desafio_picpay_transacao.business.dto.TransferDTO;
import com.example.desafio_picpay_transacao.business.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Void> createTransfer(@RequestBody TransferDTO transferDTO){
        transferService.createTransfer(transferDTO);
        return ResponseEntity.ok().build();
    }

}
