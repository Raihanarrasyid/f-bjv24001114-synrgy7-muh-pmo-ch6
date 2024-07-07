package com.binar.binarchallenge4.repository;

import com.binar.binarchallenge4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
