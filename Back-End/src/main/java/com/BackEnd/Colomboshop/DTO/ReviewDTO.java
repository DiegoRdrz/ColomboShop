package com.BackEnd.Colomboshop.DTO;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private String reviewID;
    private String userID;
    private String productID;
    private int rating;
    private String comment;
    private Date date;
}
