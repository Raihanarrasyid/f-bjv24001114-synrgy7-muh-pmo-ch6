package com.binar.binarchallenge4.service;

import com.binar.binarchallenge4.entity.Merchant;
import com.binar.binarchallenge4.entity.Product;
import com.binar.binarchallenge4.model.AddProductRequest;
import com.binar.binarchallenge4.model.UpdateProductRequest;
import com.binar.binarchallenge4.repository.MerchantRepository;
import com.binar.binarchallenge4.repository.ProductRepository;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public void add(AddProductRequest request) {
        Set<ConstraintViolation<AddProductRequest>> constraintViolations = validator.validate(request);
        if (constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        Optional<Merchant> merchantOptional = merchantRepository.findById(request.getMerchantId());
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            product.setMerchant(merchant);
        }
        logger.info("Adding product : " + product);
        productRepository.save(product);
    }

    @Transactional
    public void update(UpdateProductRequest request) {
        Set<ConstraintViolation<UpdateProductRequest>> constraintViolations = validator.validate(request);
        if (constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        Optional<Merchant> merchantOptional = merchantRepository.findById(request.getMerchantId());
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            product.setMerchant(merchant);
        }
        logger.info("Updating product : " + product);
        productRepository.save(product);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
        logger.info("deleting product with id : " + id);
    }

    public List<Product> getAll() {
        logger.info("get all products");
        return productRepository.findAll();
    }
}
