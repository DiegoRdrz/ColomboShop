package com.BackEnd.Colomboshop.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Reviews")
public class Review {
    @Id
    private String reviewID;
    private String userID;
    private String productID;
    private int rating;
    private String comment;
    private Date date;
}

