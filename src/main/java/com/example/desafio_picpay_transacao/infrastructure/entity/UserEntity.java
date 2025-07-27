package com.example.desafio_picpay_transacao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_user")
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String fullName;

    @Column(unique = true, nullable = false)
    private String document;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;


    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
