package com.binar.binarchallenge4.repository.auth;

import com.binar.binarchallenge4.entity.auth.AuthUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthUserRepository extends PagingAndSortingRepository<AuthUser, Long> {
    @Query("FROM AuthUser u WHERE LOWER(u.username) = LOWER(?1)")
    AuthUser findOneByUsername(String username);

    @Query("FROM AuthUser u WHERE u.otp = ?1")
    AuthUser findOneByOTP(String otp);

    @Query("FROM AuthUser u WHERE LOWER(u.username) = LOWER(:username)")
    AuthUser checkExistingEmail(String username);
}