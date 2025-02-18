package org.skypro.generator.service;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private Set<Question> examQuestions;

    public ExaminerServiceImpl(QuestionService questionService, Set<Question> examQuestions) {
        this.questionService = questionService;
        this.examQuestions = examQuestions;
    }

    public Set<Question> getExamQuestions() {
        return examQuestions;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        if (questionService.getAllQuestions().isEmpty()) {
            throw new EmptyStorageException();
        }
        if (amount > questionService.getAllQuestions().size()) {
            throw new TooLargeNumberRequestedException("Слишком большое запрашиваемое число.");
        }
        return examQuestions = questionService.getAllQuestions().stream()
                .limit(amount)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
