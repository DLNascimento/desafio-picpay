package com.example.desafio_picpay_transacao.infrastructure.repository;

import com.example.desafio_picpay_transacao.infrastructure.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
