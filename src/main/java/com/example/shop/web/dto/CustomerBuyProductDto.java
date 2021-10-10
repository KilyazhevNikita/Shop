package com.example.shop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerBuyProductDto {

    private int idCustomer;

    private int idProduct;

    private int quantity;

}
