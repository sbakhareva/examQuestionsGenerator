package org.skypro.generator.controller;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.JavaQuestionService;
import org.skypro.generator.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    public QuestionService getQuestionService() {
        return javaQuestionService;
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam("question") String question,
                              @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        javaQuestionService.addQuestion(newQuestion);
        return "Вопрос добавлен в список!";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam("question") String question,
                                 @RequestParam("answer") String answer) {
        javaQuestionService.removeQuestion(question, answer);
        return "Вопрос удалён из списка!";
    }

    @GetMapping
    public Set<Question> getAllQuestions() {
        return javaQuestionService.getAllQuestions();
    }

}
