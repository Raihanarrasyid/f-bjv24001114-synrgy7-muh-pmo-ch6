package com.binar.binarchallenge4.repository;

import com.binar.binarchallenge4.entity.Order_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<Order_Detail, Integer> {
}
