package com.BackEnd.Colomboshop.DTO;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class TransactionDTO {
    private String transactionID;
    private String userID;
    private String sellerID;
    private Map<String,Object> userPaymentMethod;
    private Map<String,Object> sellerPaymentMethod;
    private double amount;
    private Date date;
}
