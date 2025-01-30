package org.skypro.generator.service;
import org.skypro.generator.model.Question;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    public final HashSet<Question> questions;

    public JavaQuestionService(HashSet<Question> questions) {
        this.questions = questions;
    }

    @Override
    @Primary
    public void addQA(String question, String answer) {
        questions.add(new Question(question, answer));
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void removeQuestion(String question, String answer) {
        List<Question> questionsToRemove = questions.stream()
                .filter(q -> (q.getQuestion().toLowerCase().replace(" ", "")
                        .contains(question)) || (q.getAnswer().contains(answer)))
                .toList();
        questions.removeAll(questionsToRemove);
    }

    @Override
    public Set<Question> getAllQuestions() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random a = new Random();
        int randomNumber = a.nextInt(questions.size());
        return questions.stream().toList().get(randomNumber);
    }
}
