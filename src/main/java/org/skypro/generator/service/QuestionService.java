package org.skypro.generator.service;

import org.skypro.generator.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    void addQuestion(Question question);

    void removeQuestion(String therm);

    List<Question> getAllQuestions();

    Question getRandomQuestion();
}
