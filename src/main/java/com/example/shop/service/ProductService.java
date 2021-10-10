package com.example.shop.service;

import com.example.shop.entity.Product;
import com.example.shop.web.dto.PriceRangeDto;
import com.example.shop.web.dto.ProductDto;
import com.example.shop.web.dto.RevaluationOfProductsDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProductService {

    Product addProduct(ProductDto productDto);

    Product getProductById(int id);

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryName(String name);

    List<Product> getProductsByPriceIsBetween(PriceRangeDto priceRangeDto);

    void exportProductByExcelInCategory(HttpServletResponse response);

    List<Product> revaluationProductsInCategory(RevaluationOfProductsDto revaluationOfProductsDto);
}
