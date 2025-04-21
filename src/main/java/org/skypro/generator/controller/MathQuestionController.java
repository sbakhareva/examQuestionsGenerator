package org.skypro.generator.controller;

import jakarta.websocket.server.PathParam;
import org.skypro.generator.model.questionEx.MathQuestion;
import org.skypro.generator.service.impl.MathQuestionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final MathQuestionServiceImpl mathQuestionService;

    public MathQuestionController(MathQuestionServiceImpl mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }


    @PostMapping("/add-question")
    public MathQuestion addQuestion(@RequestBody MathQuestion question) {
        mathQuestionService.addQuestion(question);
        return question;
    }

    @GetMapping("/get-all")
    public List<MathQuestion> getAllQuestions() {
        return mathQuestionService.getAll();
    }

    @GetMapping("/get-by-id")
    public Optional<MathQuestion> getQuestionById(@PathParam("id") Long id) {
        return mathQuestionService.getQuestionById(id);
    }

    @GetMapping("/get-by-name")
    public Optional<MathQuestion> getQuestionByQuestion(@RequestParam String question) {
        return mathQuestionService.getQuestionByQuestion(question);
    }

    @DeleteMapping("/delete-question-by-question")
    public void deleteQuestionByQuestion(@RequestParam String question) {
        mathQuestionService.removeByTherm(question);
    }

    @DeleteMapping("/delete-question-by-id")
    public void deleteQuestionById(@PathParam("id") Long id) {
        mathQuestionService.removeById(id);
    }

    @PutMapping("/update-question")
    public MathQuestion updateQuestion(@RequestBody MathQuestion question) {
        mathQuestionService.update(question);
        return question;
    }

    @GetMapping("/get-random-question")
    public MathQuestion getRandomQuestion() {
        return mathQuestionService.getRandomQuestion();
    }
}
