package com.binar.binarchallenge4.service;

import com.binar.binarchallenge4.entity.Order;
import com.binar.binarchallenge4.entity.Order_Detail;
import com.binar.binarchallenge4.entity.User;
import com.binar.binarchallenge4.error.UserNotFoundException;
import com.binar.binarchallenge4.model.AddOrderRequest;
import com.binar.binarchallenge4.repository.OrderDetailRepository;
import com.binar.binarchallenge4.repository.OrderRepository;
import com.binar.binarchallenge4.repository.UserRepository;
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
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public void addOrder(AddOrderRequest request) {
        Set<ConstraintViolation<AddOrderRequest>> constraintViolations = validator.validate(request);
        if(constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        Order order = new Order();
        order.setCompleted(false);
        order.setDate(request.getDate());
        order.setDestination_address(request.getDestinationAddress());
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if(userOptional.isPresent()) {
            order.setUser(userOptional.get());
        } else {
            throw new UserNotFoundException("User not Found");
        }
        Order orderOptional = orderRepository.save(order);

        for (Order_Detail orderDetail : request.getOrderDetails()){
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }


}
