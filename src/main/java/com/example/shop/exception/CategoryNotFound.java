package com.example.shop.exception;

public class CategoryNotFound extends RuntimeException {

    public CategoryNotFound(String message) {
        super(message);
    }
}
