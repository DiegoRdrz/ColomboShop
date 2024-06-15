package com.BackEnd.Colomboshop.Service;

import com.BackEnd.Colomboshop.Model.Question;
import com.BackEnd.Colomboshop.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionsById(String id) {
        return questionRepository.findById(id);
    }

    public Question createQuestions(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestions(String id, Question question) {
        if (questionRepository.existsById(id)) {
            question.setQuestionID(id);
            return questionRepository.save(question);
        } else {
            throw new RuntimeException("Question not found");
        }
    }

    public void deleteQuestions(String id) {
        questionRepository.deleteById(id);
    }
}
