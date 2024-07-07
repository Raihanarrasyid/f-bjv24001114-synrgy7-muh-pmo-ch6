package com.binar.binarchallenge4.controller;

import com.binar.binarchallenge4.entity.User;
import com.binar.binarchallenge4.model.RegisterUserRequest;
import com.binar.binarchallenge4.model.UpdateUserRequest;
import com.binar.binarchallenge4.model.WebResponse;
import com.binar.binarchallenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(path = "/api/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<User>> getAll() {
        List<User> all = userService.getAll();
        return WebResponse.<List<User>>builder().data(all).build();
    }

    @CrossOrigin
    @PostMapping(path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @PutMapping(path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> update(@RequestBody UpdateUserRequest request) {
        userService.update(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @CrossOrigin
    @DeleteMapping(path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable int id) {
        userService.delete(id);
        return WebResponse.<String>builder().data("OK").build();
    }
}
