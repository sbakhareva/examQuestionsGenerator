package org.skypro.generator.service.impl;

import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.IncorrectValueException;
import org.skypro.generator.model.questionEx.MathQuestion;
import org.skypro.generator.repository.MathQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MathQuestionServiceImpl {

    private MathQuestionRepository mathQuestionRepository;
    private final Random random = new Random();

    public MathQuestionServiceImpl(MathQuestionRepository mathQuestions) {
        this.mathQuestionRepository = mathQuestions;
    }

    public boolean isStorageEmpty() {
        return mathQuestionRepository.findAll().isEmpty();
    }

    public void addQuestion(MathQuestion question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        List<String> q = mathQuestionRepository.findAll().stream()
                .map(MathQuestion::getQuestion)
                .toList();
        if (q.contains(question.getQuestion())) {
            throw new IncorrectValueException("Вы пытаетесь добавить вопрос, который уже есть в хранилище!");
        }
        mathQuestionRepository.save(question);
    }

    public Optional<MathQuestion> getQuestionById(Long id) {
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        return mathQuestionRepository.findById(id);
    }

    public Optional<MathQuestion> getQuestionByQuestion(String question) {
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        return mathQuestionRepository.findByQuestionIgnoreCaseContains(question);
    }

    public void removeByTherm(String therm) {
        if (therm.isBlank()) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        mathQuestionRepository.deleteByQuestionIgnoreCaseContains(therm);
    }

    public void removeById(Long id) {
        if (id == 0) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        mathQuestionRepository.deleteById(id);
    }

    public List<MathQuestion> getAll() {
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        return mathQuestionRepository.findAll();
    }

    public MathQuestion update(MathQuestion question) {
        return Optional.of(mathQuestionRepository.save(question)).orElseThrow(IncorrectValueException::new);
    }

    public MathQuestion getRandomQuestion() {
        List<MathQuestion> questions = mathQuestionRepository.findAll();
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        int randomInt = random.nextInt(questions.size());
        return questions.get(randomInt);
    }
}
