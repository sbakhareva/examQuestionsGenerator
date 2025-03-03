package org.skypro.generator.controller;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    public QuestionService getJavaQuestionService() {
        return questionService;
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam("question") String question,
                              @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        questionService.addQuestion(newQuestion);
        return "Вопрос добавлен в список!";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam("id") Long id) {
        questionService.removeQuestion(id);
        return "Вопрос удалён из списка!";
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

}
