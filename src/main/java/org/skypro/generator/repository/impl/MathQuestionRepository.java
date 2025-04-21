package org.skypro.generator.repository.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MathQuestionRepository {

    private final Random random = new Random();
    private final Map<Long, Question> mathQuestions;

    public MathQuestionRepository() {
        this.mathQuestions = new HashMap<>();
        addQuestion(new Question("2 + 2", " = 4"));
        addQuestion(new Question("3 + 3", " = 9"));
        addQuestion(new Question("4 + 4", " = 8"));
        addQuestion(new Question("5 + 5", " = 10"));
        addQuestion(new Question("4 * 4", " = 16"));
        addQuestion(new Question("7 * 7", " = 49"));
        addQuestion(new Question("1 + 1", " = 2"));
        addQuestion(new Question("11 * 11", " = 121"));
    }
    
    public void addQuestion(Question question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        List<String> q = mathQuestions.values().stream()
                .map(Question::getQuestion)
                .toList();
        if (q.contains(question.getQuestion())) {
            throw new IncorrectValueException("Вы пытаетесь добавить вопрос, который уже есть в хранилище!");
        }
        mathQuestions.put(random.nextLong(), question);
    }

    public void remove(String therm) {
        if (therm.isBlank()) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        Iterator<Map.Entry<Long, Question>> iterator = mathQuestions.entrySet().iterator();

        while (iterator.hasNext()) {
            Question q = iterator.next().getValue();
            if (q.getQuestion().contains(therm)) {
                iterator.remove();
            }
        }
    }

    public Map<Long, Question> getAll() {
        if (mathQuestions.isEmpty()) {
            throw new EmptyStorageException();
        }
        return Collections.unmodifiableMap(mathQuestions);
    }
}
