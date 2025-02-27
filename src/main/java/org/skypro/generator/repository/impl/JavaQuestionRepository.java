package org.skypro.generator.repository.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> javaQuestions;

    public JavaQuestionRepository(Set<Question> javaQuestions) {
        this.javaQuestions = javaQuestions;
        addQuestion(new Question("вопрос", "ответ"));
        addQuestion(new Question("ещё вопрос", "ещё ответ"));
        addQuestion(new Question("третий вопрос", "третий ответ"));
        addQuestion(new Question("четвертый вопрос", "ответ"));
        addQuestion(new Question("пятый вопрос", "ответ"));
        addQuestion(new Question("шестой вопрос", "ответ"));
        addQuestion(new Question("седбмой вопрос", "ответ"));
        addQuestion(new Question("восьмой вопрос", "ответ"));
        addQuestion(new Question("девятый вопрос", "ответ"));
    }

    @Override
    public void addQuestion(Question question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        javaQuestions.add(question);
    }

    @Override
    public void remove(Question question) {
        javaQuestions.removeIf(q -> q.getQuestion().contains(question.getQuestion()));
    }

    @Override
    public Set<Question> getAll() {
        if (javaQuestions.isEmpty()) {
            throw new EmptyStorageException();
        }
        return Collections.unmodifiableSet(javaQuestions);
    }
}
