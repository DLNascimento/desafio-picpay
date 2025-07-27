package com.example.desafio_picpay_transacao.infrastructure.exception;

public class UnauthorizedTransactionException extends RuntimeException{
    public UnauthorizedTransactionException(String message){
        super(message);
    }
    public UnauthorizedTransactionException(String message, Throwable throwable){
        super(message, throwable);
    }
}
