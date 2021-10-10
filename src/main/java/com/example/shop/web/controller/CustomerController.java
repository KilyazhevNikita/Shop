package com.example.shop.web.controller;

import com.example.shop.entity.Customer;
import com.example.shop.entity.Product;
import com.example.shop.service.CustomerService;
import com.example.shop.web.dto.CustomerBuyProductDto;
import com.example.shop.web.dto.CustomerDto;
import com.example.shop.web.dto.CustomerMoneyDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> buyProduct(@RequestBody CustomerBuyProductDto customerBuyProductDto) {
        return new ResponseEntity<>(customerService.buyProduct(customerBuyProductDto), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @PutMapping(value = "/addMoney", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addMoneyToCustomer(@RequestBody CustomerMoneyDto customerMoneyDto) {
        return new ResponseEntity<>(customerService.addMoneyCustomer(customerMoneyDto), HttpStatus.OK);
    }
}
