package org.skypro.generator.service;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
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
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        questions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        questions.removeIf(q -> q.getQuestion().contains(question.getQuestion()));
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
