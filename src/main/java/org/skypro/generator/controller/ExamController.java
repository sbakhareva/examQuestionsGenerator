package org.skypro.generator.controller;

import jakarta.websocket.server.PathParam;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.impl.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/get")
public class ExamController {

    private final ExaminerServiceImpl examinerServiceImpl;

    public ExamController(ExaminerServiceImpl examinerServiceImpl) {
        this.examinerServiceImpl = examinerServiceImpl;
    }


    @GetMapping
    public List<Question> getQuestions(@PathParam("amount") int amount) {
        return examinerServiceImpl.getQuestions(amount);
    }
}
