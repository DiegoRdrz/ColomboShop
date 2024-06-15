package com.BackEnd.Colomboshop.DTO;

import lombok.Data;

@Data
public class ShoppingCartDTO {

    private String shoppingCartID;
    private String productID;
    private double quantity;
}
