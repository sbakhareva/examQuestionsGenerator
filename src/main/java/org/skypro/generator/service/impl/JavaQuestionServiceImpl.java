package org.skypro.generator.service.impl;

import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.IncorrectValueException;
import org.skypro.generator.model.questionEx.JavaQuestion;
import org.skypro.generator.repository.JavaQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionServiceImpl {

    private final JavaQuestionRepository javaQuestionRepository;
    private final Random random = new Random();

    public JavaQuestionServiceImpl(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    public boolean isStorageEmpty() {
        return javaQuestionRepository.findAll().isEmpty();
    }

    public void addQuestion(JavaQuestion question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        List<String> q = javaQuestionRepository.findAll().stream()
                .map(JavaQuestion::getQuestion)
                .toList();
        if (q.contains(question.getQuestion())) {
            throw new IncorrectValueException("Вы пытаетесь добавить вопрос, который уже есть в хранилище!");
        }
        javaQuestionRepository.save(question);
    }

    public Optional<JavaQuestion> getQuestionById(Long id) {
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        return javaQuestionRepository.findById(id);
    }

    public Optional<JavaQuestion> getQuestionByQuestion(String question) {
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        return javaQuestionRepository.findByQuestionIgnoreCaseContains(question);
    }

    public void removeByTherm(String therm) {
        if (therm.isBlank()) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        javaQuestionRepository.deleteByQuestionIgnoreCaseContains(therm);
    }

    public void removeById(Long id) {
        if (id == 0) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        javaQuestionRepository.deleteById(id);
    }

    public List<JavaQuestion> getAll() {
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        return javaQuestionRepository.findAll();
    }

    public JavaQuestion update(JavaQuestion question) {
        return Optional.of(javaQuestionRepository.save(question)).orElseThrow(IncorrectValueException::new);
    }

    public JavaQuestion getRandomQuestion() {
        List<JavaQuestion> questions = javaQuestionRepository.findAll();
        if (isStorageEmpty()) {
            throw new EmptyStorageException();
        }
        int randomInt = random.nextInt(questions.size());
        return questions.get(randomInt);
    }
}
