package com.binar.binarchallenge4.service;

import com.binar.binarchallenge4.entity.User;
import com.binar.binarchallenge4.model.RegisterUserRequest;
import com.binar.binarchallenge4.model.UpdateUserRequest;
import com.binar.binarchallenge4.repository.UserRepository;
import com.binar.binarchallenge4.security.BCrypt;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public void register(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
        if(constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));
        user.setEmail(request.getEmail());

        userRepository.save(user);
        logger.info("User registered : " + request.getUsername() + " - " + request.getPassword() + " - " + request.getEmail());
    }

    @Transactional
    public void update(UpdateUserRequest request) {
        Set<ConstraintViolation<UpdateUserRequest>> constraintViolations = validator.validate(request);
        if(constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        User user = userRepository.findById(request.getId()).get();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));
        userRepository.save(user);
        logger.info("User updated : " + request.getId() + " - " + request.getUsername() + " - " + request.getPassword() + " - " + request.getEmail());   
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
        logger.info("User deleted : " + id);
    }

    public User findById(int id) {
        logger.info("find user by id : " + id);
        return userRepository.findById(id).get();
    }

    public List<User> getAll() {
        logger.info("returning all user");
        return userRepository.findAll();
    }
}
