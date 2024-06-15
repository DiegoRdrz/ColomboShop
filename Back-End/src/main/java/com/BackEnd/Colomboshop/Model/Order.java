package com.BackEnd.Colomboshop.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Data
@Document(collection = "Orders")
public class Order {
    @Id
    private String orderID;
    private String userID;
    private Map<String, Object> orderDetails;
    private String shippingAddress;
    private String orderStatus;
}

