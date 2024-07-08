package com.binar.binarchallenge4.config;

import org.springdoc.core.providers.RepositoryRestConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.context.WebApplicationContext;

@Configuration
public class RestRepositoryConfiguration {
    @Autowired
    private WebApplicationContext context;

}
