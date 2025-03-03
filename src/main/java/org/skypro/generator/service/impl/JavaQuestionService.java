package org.skypro.generator.service.impl;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.impl.JavaQuestionRepository;
import org.skypro.generator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;

    @Autowired
    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public void addQuestion(Question question) {
        javaQuestionRepository.addQuestion(question);
    }

    @Override
    public void removeQuestion(String therm) {
        javaQuestionRepository.remove(therm);
    }

    @Override
    public Map<Long, Question> getAllQuestions() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestionRepository.getAll().isEmpty()) {
            throw new EmptyStorageException();
        }
        Random a = new Random();
        long randomNumber = a.nextLong(javaQuestionRepository.getAll().size());
        return javaQuestionRepository.getAll().get(randomNumber);
    }

    @Override
    public String toString() {
        return "JavaQuestionService{" +
                "questions=" + javaQuestionRepository.getAll() +
                '}';
    }
}
