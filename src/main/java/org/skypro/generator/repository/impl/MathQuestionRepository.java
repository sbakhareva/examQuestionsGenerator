package org.skypro.generator.repository.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> mathQuestions;

    public MathQuestionRepository(Set<Question> mathQuestions) {
        this.mathQuestions = mathQuestions;
        /*
        addQuestion(new Question("2 + 2", " = 4"));
        addQuestion(new Question("3 + 3", " = 9"));
        addQuestion(new Question("4 + 4", " = 8"));
        addQuestion(new Question("5 + 5", " = 10"));
        addQuestion(new Question("4 * 4", " = 16"));
        addQuestion(new Question("7 * 7", " = 49"));
        addQuestion(new Question("1 + 1", " = 2"));
        addQuestion(new Question("11 * 11", " = 121"));

         */
    }


    @Override
    public void addQuestion(Question question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        mathQuestions.add(question);
    }

    @Override
    public void remove(Question question) {
        mathQuestions.removeIf(q -> q.getQuestion().contains(question.getQuestion()));
    }

    @Override
    public Set<Question> getAll() {
        if (mathQuestions.isEmpty()) {
            throw new EmptyStorageException();
        }
        return Collections.unmodifiableSet(mathQuestions);
    }
}
