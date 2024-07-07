package com.binar.binarchallenge4.repository.auth;

import com.binar.binarchallenge4.entity.auth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client findOneByClientId(String clientId);
}
