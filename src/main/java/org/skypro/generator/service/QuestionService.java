package org.skypro.generator.service;

import org.skypro.generator.model.Question;

import java.util.Map;

public interface QuestionService {

    void addQuestion(Question question);

    void removeQuestion(String therm);

    Map<Long, Question> getAllQuestions();

    Question getRandomQuestion();
}
