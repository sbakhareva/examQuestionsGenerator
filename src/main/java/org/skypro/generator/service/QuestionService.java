package org.skypro.generator.service;

import org.skypro.generator.model.Question;

import java.util.List;

public interface QuestionService {

    void addQuestion(Question question);

    void removeQuestion(Long id);

    List<Question> getAllQuestions();

    Question getRandomQuestion();
}
