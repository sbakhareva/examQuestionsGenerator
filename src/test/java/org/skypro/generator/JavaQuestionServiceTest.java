package org.skypro.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.JavaQuestionService;
import org.xmlunit.util.Linqy;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private HashSet<Question> questions;

    @InjectMocks
    JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        questions = Mockito.spy(new HashSet<>());
        javaQuestionService = new JavaQuestionService(questions);
    }

    @Test
    void addQuestion_WhenQuestionIsNotNull() {
        Question question = new Question("aaaaa", "oooooo");
        javaQuestionService.addQuestion(question);
        Mockito.verify(questions, times(1)).add(question);
    }

    @Test
    void addQA_WhenSameIsAlreadyInStorage() {
        Question q1 = new Question("3", "4");
        Question q2 = new Question("3", "4");
        javaQuestionService.addQuestion(q1);
        Mockito.verify(questions, times(1)).add(q1);
        javaQuestionService.addQuestion(q2);
        assertEquals(1, questions.size()); // проверяю что нет возможности добавить два одинаковых вопроса
    }

    @Test
    void addQuestion_WhenValueIsEmpty() {
        Question q1 = new Question("    ", "answer");
        Assertions.assertThrows(IncorrectValueException.class, () -> javaQuestionService.addQuestion(q1));
    }

    @Test
    void removeQuestionTest() {

    }

    @Test
    void getAllQuestions_WhenStorageIsEmpty() {

    }

    @Test
    void getAllQuestions_WhenThereAreQuestionsInStorage() {

    }

    @Test
    void getRandomQuestion_WhenStorageIsEmpty() {

    }

    @Test
    void getRandomQuestion_WhenThereAreQuestionsInStorage() {

    }
}
