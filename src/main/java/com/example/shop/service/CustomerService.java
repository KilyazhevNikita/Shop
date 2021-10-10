package com.example.shop.service;

import com.example.shop.entity.Customer;
import com.example.shop.entity.Product;
import com.example.shop.web.dto.CustomerBuyProductDto;
import com.example.shop.web.dto.CustomerDto;
import com.example.shop.web.dto.CustomerMoneyDto;

import java.util.List;

public interface CustomerService {

    Product buyProduct(CustomerBuyProductDto customerBuyProductDto);

    Customer addCustomer(CustomerDto customerDto);

    Customer addMoneyCustomer(CustomerMoneyDto customerMoneyDto);

    Customer getCustomerById(int id);

    List<Customer> getCustomers();
}
