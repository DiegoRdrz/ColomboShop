package com.BackEnd.Colomboshop.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Categories")
public class Category {
    @Id
    private String categoryID;
    private String name;
    private String description;
}
