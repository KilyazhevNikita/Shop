package com.example.shop.export;

import com.example.shop.entity.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductCategoryExcelExporter {

    private Map<String, List<Product>> productMap;
    private HttpServletResponse response;

    public ProductCategoryExcelExporter(HttpServletResponse response, Map<String, List<Product>> productMap) {
        this.response = response;
        this.productMap = productMap;
    }

    public void createExcel() {

        XSSFWorkbook workbook = new XSSFWorkbook();

        for (Map.Entry<String, List<Product>> entry : productMap.entrySet()) {
            createListExcel(workbook, entry.getKey(), entry.getValue());
        }

        try (ServletOutputStream outputStream = response.getOutputStream();) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createListExcel(XSSFWorkbook workbook, String nameSheet, List<Product> products) {
        XSSFSheet sheet = workbook.createSheet(nameSheet);
        int rowNum = 0;
        for (Product product : products) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getPrice());
        }
    }
}
