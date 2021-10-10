package com.example.shop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevaluationOfProductsDto {

    private String categoryName;

    private int percent;
}
