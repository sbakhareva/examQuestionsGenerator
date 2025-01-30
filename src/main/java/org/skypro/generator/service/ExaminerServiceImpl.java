package org.skypro.generator.service;

import org.skypro.generator.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<Question> examQuestions;

    public ExaminerServiceImpl(List<Question> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public List<Question> getExamQuestions() {
        return examQuestions;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        return null;
    }
}
