package com.BackEnd.Colomboshop.Model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Products")
public class Product {

    @Id
    private String productID;
    private String userID;
    private String categoryID;
    private String nameProduct;
    private String description;
    private double price;
    private int stock;
}