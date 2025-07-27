package com.example.desafio_picpay_transacao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_transfer")
@Builder
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private UserEntity payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private UserEntity payee;

    private LocalDateTime localDateTime;


}
