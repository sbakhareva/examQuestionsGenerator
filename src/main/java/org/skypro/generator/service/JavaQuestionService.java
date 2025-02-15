package org.skypro.generator.service;
import org.skypro.generator.model.Question;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    public final HashSet<Question> questions;

    public JavaQuestionService(HashSet<Question> questions) {
        this.questions = questions;
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void removeQuestion(String question, String answer) {
        List<Question> questionsToRemove = questions.stream()
                .filter(q -> ((q.getQuestion().toLowerCase().replace(" ", "")
                        .contains(question)) || (q.getAnswer().contains(answer)))
                        || (q.getAnswer().toLowerCase().replace(" ", "")
                        .contains(q.getAnswer().toLowerCase().replace(" ", ""))))
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

    @Override
    public String toString() {
        return "JavaQuestionService{" +
                "questions=" + questions +
                '}';
    }
}
