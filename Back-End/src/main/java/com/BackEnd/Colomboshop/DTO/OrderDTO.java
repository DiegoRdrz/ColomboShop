package com.BackEnd.Colomboshop.DTO;

import lombok.Data;
import java.util.Map;

@Data
public class OrderDTO {
    private String orderID;
    private String userID;
    private Map<String, Object> orderDetails;
    private String shippingAddress;
    private String orderStatus;
}

