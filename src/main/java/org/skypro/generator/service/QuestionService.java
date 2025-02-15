package org.skypro.generator.service;

import org.skypro.generator.model.Question;

import java.util.Set;

public interface QuestionService {

    void addQuestion(Question question);

    void removeQuestion(Question question);

    Set<Question> getAllQuestions();

    Question getRandomQuestion();
}
