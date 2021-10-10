package com.example.shop.web.controller;

import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import com.example.shop.web.dto.PriceRangeDto;
import com.example.shop.web.dto.ProductDto;
import com.example.shop.web.dto.RevaluationOfProductsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/addProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.OK);
    }

    @GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String name) {
        return new ResponseEntity<>(productService.getProductsByCategoryName(name), HttpStatus.OK);
    }

    @PostMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsByPriceBetween(@RequestBody PriceRangeDto priceRangeDto) {
        return new ResponseEntity<>(productService.getProductsByPriceIsBetween(priceRangeDto), HttpStatus.OK);
    }

    @GetMapping(value = "/export/productExcel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> exportToExcel(HttpServletResponse response) {
        productService.exportProductByExcelInCategory(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/category/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> revaluationProductsInCategory(RevaluationOfProductsDto revaluationOfProductsDto) {
        return new ResponseEntity<>(productService.revaluationProductsInCategory(revaluationOfProductsDto), HttpStatus.OK);
    }
}