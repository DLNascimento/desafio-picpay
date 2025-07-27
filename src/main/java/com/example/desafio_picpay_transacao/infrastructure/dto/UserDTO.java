package com.example.desafio_picpay_transacao.infrastructure.dto;

import com.example.desafio_picpay_transacao.infrastructure.entity.UserType;

import java.math.BigDecimal;

public record UserDTO(String fullname, String document, String email, String password,
                      BigDecimal balance, UserType userType) {
}
