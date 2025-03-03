package org.skypro.generator.repository.impl;

import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private final Random random = new Random();
    private final HashMap<Long, Question> javaQuestions;

    public JavaQuestionRepository(HashMap<Long, Question> javaQuestions) {
        this.javaQuestions = javaQuestions;
        /*
        addQuestion(new Question("вопрос", "ответ"));
        addQuestion(new Question("ещё вопрос", "ещё ответ"));
        addQuestion(new Question("третий вопрос", "третий ответ"));
        addQuestion(new Question("четвертый вопрос", "ответ"));
        addQuestion(new Question("пятый вопрос", "ответ"));
        addQuestion(new Question("шестой вопрос", "ответ"));
        addQuestion(new Question("седбмой вопрос", "ответ"));
        addQuestion(new Question("восьмой вопрос", "ответ"));
        addQuestion(new Question("девятый вопрос", "ответ"));

         */
    }

    @Override
    public void addQuestion(Question question) {
        if (question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            throw new IncorrectValueException();
        }
        List<String> q = javaQuestions.values().stream()
                .map(Question::getQuestion)
                .toList();
        if (q.contains(question.getQuestion())) {
            throw new IncorrectValueException("Вы пытаетесь добавить вопрос, который уже есть в хранилище!");
        }
        javaQuestions.put(random.nextLong(), question);
    }

    @Override
    public void remove(String therm) {
        if (therm.isBlank()) {
            throw new RuntimeException("Строка поиска пуста!");
        }
        Iterator<Map.Entry<Long, Question>> iterator = javaQuestions.entrySet().iterator();

        while (iterator.hasNext()) {
            Question q = iterator.next().getValue();
            if (q.getQuestion().contains(therm)) {
                iterator.remove();
            }
        }
    }

    @Override
    public Map<Long, Question> getAll() {
        if (javaQuestions.isEmpty()) {
            throw new EmptyStorageException();
        }
        return Collections.unmodifiableMap(javaQuestions);
    }
}
