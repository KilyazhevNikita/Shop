package com.example.shop.web.handler;


import com.example.shop.exception.CategoryNotFound;
import com.example.shop.exception.CustomerNotFound;
import com.example.shop.exception.NotEnoughFunds;
import com.example.shop.exception.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomerConflict(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleProductConflict(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<String> handleCategoryConflict(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughFunds.class)
    public ResponseEntity<String> handleMoneyConflict(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
