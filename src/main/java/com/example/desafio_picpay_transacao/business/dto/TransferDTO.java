package com.example.desafio_picpay_transacao.business.dto;

import java.math.BigDecimal;

public record TransferDTO(BigDecimal value, Long payer, Long payee) {
}
