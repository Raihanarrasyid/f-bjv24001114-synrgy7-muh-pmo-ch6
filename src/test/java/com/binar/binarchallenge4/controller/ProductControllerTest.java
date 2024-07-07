package com.binar.binarchallenge4.controller;

import com.binar.binarchallenge4.model.AddProductRequest;
import com.binar.binarchallenge4.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void testGetAllSuccess() throws Exception {
        mockMvc.perform(
            get("/api/product")
        ).andExpectAll(status().isOk());
    }

    @Test
    void testAddProductSuccess() throws Exception {
        AddProductRequest request = new AddProductRequest();
        request.setName("Product 1");
        request.setPrice(10000);
        request.setMerchantId(0);
    }
}