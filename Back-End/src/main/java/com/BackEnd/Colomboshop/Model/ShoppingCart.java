package com.BackEnd.Colomboshop.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ShoppingCart")
public class ShoppingCart {

    @Id
    private String shoppingCartID;
    private String productId;
    private int quantity;
}

