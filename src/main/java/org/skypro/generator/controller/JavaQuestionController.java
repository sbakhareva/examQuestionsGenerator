package org.skypro.generator.controller;

import jakarta.websocket.server.PathParam;
import org.skypro.generator.model.questionEx.JavaQuestion;
import org.skypro.generator.service.impl.JavaQuestionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionServiceImpl javaQuestionService;

    public JavaQuestionController(JavaQuestionServiceImpl javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @PostMapping("/add-question")
    public JavaQuestion addQuestion(@RequestBody JavaQuestion question) {
        javaQuestionService.addQuestion(question);
        return question;
    }

    @GetMapping("/get-all")
    public List<JavaQuestion> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @GetMapping("/get-by-id")
    public Optional<JavaQuestion> getQuestionById(@PathParam("id") Long id) {
        return javaQuestionService.getQuestionById(id);
    }

    @GetMapping("/get-by-name")
    public Optional<JavaQuestion> getQuestionByQuestion(@RequestParam String question) {
        return javaQuestionService.getQuestionByQuestion(question);
    }

    @DeleteMapping("/delete-question-by-question")
    public void deleteQuestionByQuestion(@RequestParam String question) {
        javaQuestionService.removeByTherm(question);
    }

    @DeleteMapping("/delete-question-by-id")
    public void deleteQuestionById(@PathParam("id") Long id) {
        javaQuestionService.removeById(id);
    }

    @PutMapping("/update-question")
    public JavaQuestion updateQuestion(@RequestBody JavaQuestion question) {
        javaQuestionService.update(question);
        return question;
    }

    @GetMapping("/get-random-question")
    public JavaQuestion getRandomQuestion() {
        return javaQuestionService.getRandomQuestion();
    }
}
