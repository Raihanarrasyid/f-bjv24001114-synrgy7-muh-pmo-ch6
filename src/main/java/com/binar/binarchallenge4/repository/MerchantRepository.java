package com.binar.binarchallenge4.repository;

import com.binar.binarchallenge4.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    List<Merchant> findByOpen(boolean isOpen);
}
