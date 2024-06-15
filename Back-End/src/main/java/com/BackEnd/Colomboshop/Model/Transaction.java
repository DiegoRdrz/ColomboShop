package com.BackEnd.Colomboshop.Model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@Document(collection = "Transactions")
public class Transaction {

    @Id
    private String transactionID;
    private String userID;
    private String sellerID;
    private Map<String,Object> userPaymentMethod;
    private Map<String,Object> sellerPaymentMethod;
    private double amount;
    private Date date;

}

