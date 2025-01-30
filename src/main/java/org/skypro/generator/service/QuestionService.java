package org.skypro.generator.service;

import org.skypro.generator.model.Question;

import java.util.Set;

public interface QuestionService {

    void addQA(String question, String answer);

    void addQuestion(Question question);

    void removeQuestion(String question, String answer);

    Set<Question> getAllQuestions();

    Question getRandomQuestion();
}
