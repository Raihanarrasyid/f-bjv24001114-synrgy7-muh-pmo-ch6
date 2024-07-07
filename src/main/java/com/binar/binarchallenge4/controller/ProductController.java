package com.binar.binarchallenge4.controller;

import com.binar.binarchallenge4.entity.Product;
import com.binar.binarchallenge4.model.AddProductRequest;
import com.binar.binarchallenge4.model.UpdateProductRequest;
import com.binar.binarchallenge4.model.WebResponse;
import com.binar.binarchallenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @CrossOrigin
    @PostMapping(path = "/api/product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> addProduct(@RequestBody AddProductRequest request) {
        productService.add(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @PutMapping(path = "/api/product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> updateProduct(@RequestBody UpdateProductRequest request) {
        productService.update(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @DeleteMapping(path = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> deleteProduct(@PathVariable int productId) {
        productService.delete(productId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @GetMapping(path = "/api/product",produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<Product>> getProduct() {
        List<Product> products = productService.getAll();
        return WebResponse.<List<Product>>builder().data(products).build();
    }

}
