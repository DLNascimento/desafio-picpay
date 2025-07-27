package com.example.desafio_picpay_transacao.business.service;

import com.example.desafio_picpay_transacao.business.dto.TransferDTO;
import com.example.desafio_picpay_transacao.infrastructure.dto.TransferAuthorizationDTO;
import com.example.desafio_picpay_transacao.infrastructure.entity.TransferEntity;
import com.example.desafio_picpay_transacao.infrastructure.entity.UserEntity;
import com.example.desafio_picpay_transacao.infrastructure.entity.UserType;
import com.example.desafio_picpay_transacao.infrastructure.exception.UnauthorizedTransactionException;
import com.example.desafio_picpay_transacao.infrastructure.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final RestTemplate restTemplate;


    @Value("${authorization.response.url}")
    private String authorizationUrl;
    @Value("${notification.response.url}")
    private String notificationUrl;


    @Transactional
    public void createTransfer(TransferDTO dto) {

        UserEntity payer = userService.findUserById(dto.payer());
        UserEntity payee = userService.findUserById(dto.payee());
        userTypeValidation(payer);
        balanceTransferValidation(payer, dto.value());
        authorizationResponse();

        TransferEntity newTransfer = new TransferEntity();
        newTransfer.setAmount(dto.value());
        newTransfer.setPayer(payer);
        newTransfer.setPayee(payee);
        newTransfer.setLocalDateTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(dto.value()));
        payee.setBalance(payee.getBalance().add(dto.value()));

        transferRepository.save(newTransfer);

        notificationResponse();


    }

    public void userTypeValidation(UserEntity userEntity) {

        if (userEntity.getUserType().equals(UserType.MERCHANT)) {
            throw new IllegalArgumentException("Transação indisponível para o tipo de usuário");
        }

    }

    public void balanceTransferValidation(UserEntity userEntity, BigDecimal value) {
        if (userEntity.getBalance().compareTo(value) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    public void authorizationResponse() {
        try {
            ResponseEntity<TransferAuthorizationDTO> authorizationResponse =
                    restTemplate.getForEntity(authorizationUrl, TransferAuthorizationDTO.class);
            if (authorizationResponse.getStatusCode()
                    == HttpStatus.OK) {
                assert authorizationResponse.getBody() != null;
                if (authorizationResponse.getBody().data().authorization()) {
                    return;
                }
            }

        } catch (Exception e) {
            e.getCause();
        }
        throw new UnauthorizedTransactionException("Transação não autorizada");
    }

    public void notificationResponse() {

        ResponseEntity<String> notificationResponse =
                restTemplate.postForEntity(notificationUrl, null, String.class);
    }
}
