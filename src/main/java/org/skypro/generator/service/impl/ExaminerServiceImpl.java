package org.skypro.generator.service.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.ExaminerService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;

    private final MathQuestionService mathQuestionService;
    private final Set<Question> questions;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService, Set<Question> questions) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.questions = questions;
    }

    public Set<Question> getExamQuestions() {
        questions.addAll(javaQuestionService.getAllQuestions());
        questions.addAll(mathQuestionService.getAllQuestions());
        return questions;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        if (getExamQuestions().isEmpty()) {
            throw new EmptyStorageException();
        }
        if (amount > getExamQuestions().size()) {
            throw new TooLargeNumberRequestedException("Слишком большое запрашиваемое число.");
        }
        return getExamQuestions().stream()
                .limit(amount)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
