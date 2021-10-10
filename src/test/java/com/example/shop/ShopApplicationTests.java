package com.example.shop;

import com.example.shop.web.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerController customerController;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(
                get("/shop/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
