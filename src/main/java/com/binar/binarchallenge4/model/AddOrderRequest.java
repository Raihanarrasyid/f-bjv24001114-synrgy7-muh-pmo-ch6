package com.binar.binarchallenge4.model;

import com.binar.binarchallenge4.entity.Order_Detail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOrderRequest {
    private Date date;


    private String destinationAddress;


    private int userId;


    private List<Order_Detail> orderDetails;
}
