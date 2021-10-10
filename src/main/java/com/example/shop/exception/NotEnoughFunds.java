package com.example.shop.exception;

public class NotEnoughFunds extends RuntimeException {

    public NotEnoughFunds(String message) {
        super(message);
    }
}
