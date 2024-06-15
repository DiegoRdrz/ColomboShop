package com.BackEnd.Colomboshop.Controller;

import com.BackEnd.Colomboshop.DTO.QuestionDTO;
import com.BackEnd.Colomboshop.Model.Question;
import com.BackEnd.Colomboshop.Service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colomboShop/admin/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return questions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionsById(@PathVariable String id) {
        Optional<Question> questions = questionService.getQuestionsById(id);
        return questions.map(question -> ResponseEntity.ok(convertToDto(question)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestions(@RequestBody QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        Question createdQuestion = questionService.createQuestions(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdQuestion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestions(@PathVariable String id, @RequestBody QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        Question updatedQuestion = questionService.updateQuestions(id, question);
        return ResponseEntity.ok(convertToDto(updatedQuestion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestions(@PathVariable String id) {
        questionService.deleteQuestions(id);
        return ResponseEntity.noContent().build();
    }

    private QuestionDTO convertToDto(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        return questionDTO;
    }

    private Question convertToEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        return question;
    }
}
