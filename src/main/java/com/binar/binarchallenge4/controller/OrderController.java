package com.binar.binarchallenge4.controller;

import com.binar.binarchallenge4.entity.Order;
import com.binar.binarchallenge4.entity.Product;
import com.binar.binarchallenge4.model.AddOrderRequest;
import com.binar.binarchallenge4.model.WebResponse;
import com.binar.binarchallenge4.service.OrderService;
import com.binar.binarchallenge4.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ReportService reportService;

    @CrossOrigin
    @PostMapping(path = "/api/order", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> placeOrder(@RequestBody AddOrderRequest request) {
        orderService.addOrder(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @GetMapping(path = "/api/order",produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        return WebResponse.<List<Order>>builder().data(orders).build();
    }

    @CrossOrigin
    @GetMapping(path = "/api/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String exportReport(@PathVariable String id) {
        try {
            return reportService.exportReport(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
