package com.example.shop.service.impl;

import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.exception.CategoryNotFound;
import com.example.shop.exception.ProductNotFound;
import com.example.shop.export.ProductCategoryExcelExporter;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import com.example.shop.web.dto.PriceRangeDto;
import com.example.shop.web.dto.ProductDto;
import com.example.shop.web.dto.RevaluationOfProductsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setCategory(optionalCategory.get());
            return productRepository.save(product);
        } else throw new CategoryNotFound("no category name found by id");
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFound("Couldn't find product by id"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryName(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        if (category == null) {
            throw new CategoryNotFound("Category not found by name");
        }
        return categoryRepository.findCategoryByName(name).getProducts();
    }

    @Override
    public List<Product> getProductsByPriceIsBetween(PriceRangeDto priceRangeDto) {
        return productRepository.findProductsByPriceIsBetween(priceRangeDto.getBeforePrice(), priceRangeDto.getAfterPrice());
    }

    @Override
    public void exportProductByExcelInCategory(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=category_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Category> categories = categoryRepository.findAll();
        Map<String, List<Product>> productMap = categories
                .stream()
                .collect(Collectors.toMap(Category::getName, Category::getProducts));

        ProductCategoryExcelExporter excelExporter = new ProductCategoryExcelExporter(response, productMap);
        excelExporter.createExcel();
    }

    @Override
    public List<Product> revaluationProductsInCategory(RevaluationOfProductsDto revaluationOfProductsDto) {
        Category category = categoryRepository.findCategoryByName(revaluationOfProductsDto.getCategoryName());
        List<Product> products = category.getProducts();
        if (products.isEmpty()) {
            throw new ProductNotFound("Couldn't find product in category");
        }
        products.forEach(product ->
                product.setPrice(product.getPrice() * revaluationOfProductsDto.getPercent() / 100));
        return productRepository.saveAll(products);
    }
}
