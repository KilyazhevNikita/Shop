package com.example.shop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerMoneyDto {

    private int customerId;

    private double money;
}
