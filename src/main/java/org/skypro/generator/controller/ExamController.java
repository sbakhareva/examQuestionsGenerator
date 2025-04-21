package org.skypro.generator.controller;

import org.skypro.generator.model.Question;
import org.skypro.generator.service.impl.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/exam/get")
public class ExamController {
    private final ExaminerServiceImpl examinerServiceImpl;

    public ExamController(ExaminerServiceImpl examinerServiceImpl) {
        this.examinerServiceImpl = examinerServiceImpl;
    }

    public ExaminerServiceImpl getExaminerServiceImpl() {
        return examinerServiceImpl;
    }

    @GetMapping
    public Set<Question> getQuestions(@RequestParam("amount") int amount) {
        return examinerServiceImpl.getQuestions(amount);
    }
}
