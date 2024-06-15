package com.BackEnd.Colomboshop.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Questions")
public class Question {
    @Id
    private String questionID;
    private String content;
    private String answer;
}
