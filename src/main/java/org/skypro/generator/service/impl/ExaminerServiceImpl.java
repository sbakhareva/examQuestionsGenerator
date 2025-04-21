package org.skypro.generator.service.impl;

import org.skypro.generator.model.Question;
import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.service.ExaminerService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionServiceImpl javaQuestionService;
    private final MathQuestionServiceImpl mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionServiceImpl javaQuestionService, MathQuestionServiceImpl mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }


    @Override
    public List<Question> getQuestions(int amount) {
        List<Question> questions = new ArrayList<>();

        questions.addAll(javaQuestionService.getAll().stream()
                .map(q -> {
                    return new Question(
                            q.getQuestion(),
                            q.getAnswer());
                })
                .toList());

        questions.addAll(mathQuestionService.getAll().stream()
                .map(q -> {
                    return new Question(
                            q.getQuestion(),
                            q.getAnswer());
                })
                .toList());

        if (questions.isEmpty()) {
            throw new EmptyStorageException();
        }
        if (amount > questions.size()) {
            throw new TooLargeNumberRequestedException();
        }
        return questions.stream()
                .limit(amount)
                .toList();
    }
}
