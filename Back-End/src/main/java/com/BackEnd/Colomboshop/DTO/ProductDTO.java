package com.BackEnd.Colomboshop.DTO;

import lombok.Data;

@Data
public class ProductDTO {

    private String productID;
    private String categoryID;
    private String nameProduct;
    private String description;
    private double price;
    private int stock;
}
