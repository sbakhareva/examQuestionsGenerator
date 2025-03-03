package org.skypro.generator.controller;

import org.skypro.generator.model.Question;
import org.skypro.generator.service.impl.MathQuestionService;
import org.skypro.generator.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionsController {

    private final MathQuestionService mathQuestionService;

    public MathQuestionsController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    public QuestionService getMathQuestionService() {
        return mathQuestionService;
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam("question") String question,
                              @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        mathQuestionService.addQuestion(newQuestion);
        return "Вопрос добавлен в список!";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam("question") String question) {
        mathQuestionService.removeQuestion(question);
        return "Вопрос удалён из списка!";
    }

    @GetMapping
    public Set<Question> getAllQuestions() {
        return new HashSet<>(mathQuestionService.getAllQuestions().values());
    }
}
