package org.skypro.generator.service.impl;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.QuestionRepository;
import org.skypro.generator.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public void addQuestion(Question question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        List<String> q = questionRepository.findAll().stream()
                .map(Question::getQuestion)
                .toList();
        if (q.contains(question.getQuestion())) {
            throw new IncorrectValueException("Вы пытаетесь добавить вопрос, который уже есть в хранилище!");
        }
        questionRepository.save(question);
    }

    @Override
    public void removeQuestion(Long id) {
        if (questionRepository.getById(id).getQuestion().isBlank()) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new EmptyStorageException();
        }
        return Collections.unmodifiableList(questions);
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new EmptyStorageException();
        }
        Random a = new Random();
        int randomNumber = a.nextInt(questions.size());
        return questions.get(randomNumber);
    }
}
