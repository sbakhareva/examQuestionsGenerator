package org.skypro.generator.service.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.impl.MathQuestionRepository;
import org.skypro.generator.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private final MathQuestionRepository mathQuestionRepository;

    public MathQuestionService(MathQuestionRepository MathQuestionRepository) {
        this.mathQuestionRepository = MathQuestionRepository;
    }

    @Override
    public void addQuestion(Question question) {
        mathQuestionRepository.addQuestion(question);
    }

    @Override
    public void removeQuestion(String therm) {
        mathQuestionRepository.remove(therm);
    }

    @Override
    public Map<Long, Question> getAllQuestions() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (mathQuestionRepository.getAll().isEmpty()) {
            throw new EmptyStorageException();
        }
        Random a = new Random();
        Long randomNumber = a.nextLong(mathQuestionRepository.getAll().size());
        return mathQuestionRepository.getAll().get(randomNumber);
    }
}

