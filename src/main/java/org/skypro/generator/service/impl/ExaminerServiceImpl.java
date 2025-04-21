package org.skypro.generator.service.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.ExaminerService;
import org.skypro.generator.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        List<Question> questions = questionService.getAllQuestions();
        if (questions.isEmpty()) {
            throw new EmptyStorageException();
        }
        if (amount > questions.size()) {
            throw new TooLargeNumberRequestedException();
        }
        return questions.stream()
                .limit(amount)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
