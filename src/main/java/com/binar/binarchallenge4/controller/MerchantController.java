package com.binar.binarchallenge4.controller;


import com.binar.binarchallenge4.entity.Merchant;
import com.binar.binarchallenge4.model.RegisterMerchantRequest;
import com.binar.binarchallenge4.model.WebResponse;
import com.binar.binarchallenge4.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class MerchantController {

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @CrossOrigin
    @PostMapping(path = "/api/merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterMerchantRequest request) {
        merchantService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @PutMapping(path = "/api/merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> status(@PathVariable boolean status) {
        merchantService.setStatus(status);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @GetMapping(path = "/api/merchant", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<Merchant>> openMerchant() {
        List<Merchant> merchants = merchantService.findOpen();
        logger.info(merchants.toString());
        return WebResponse.<List<Merchant>>builder().data(merchants).build();
    }
}
