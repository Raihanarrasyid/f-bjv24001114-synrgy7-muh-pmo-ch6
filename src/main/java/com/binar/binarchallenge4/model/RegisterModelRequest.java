package com.binar.binarchallenge4.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RegisterModelRequest {

    public Long id;

    @NotBlank(message = "email is required")
    public String email;

    @NotBlank(message = "username is required")
    public String username;

    @NotBlank(message = "password is required")
    public String password;
}