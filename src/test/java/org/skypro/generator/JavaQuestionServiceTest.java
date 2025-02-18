package org.skypro.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.JavaQuestionService;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Spy
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
        Question q1 = new Question("1", "2");
        Question q2 = new Question("3", "4");
        javaQuestionService.addQuestion(q1);
        javaQuestionService.addQuestion(q2);
        javaQuestionService.removeQuestion(q1);
        Assertions.assertEquals(1, questions.size());
        Assertions.assertFalse(questions.toString().contains("1"));
        Assertions.assertTrue(questions.toString().contains("3"));

    }

    @Test
    void getAllQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> javaQuestionService.getAllQuestions());
    }

    // здесь кажется тест не особо иметт смысл, потому что корректный вызов getAllQuestions не потверить через verify
    @Test
    void getAllQuestions_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaaaa", "ooooooooo");
        javaQuestionService.addQuestion(q1);
        Mockito.verify(questions).add(q1);
        Assertions.assertTrue(questions.contains(q1));
    }

    @Test
    void getRandomQuestion_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> javaQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("uuuuu", "aaaaaaaa");
        javaQuestionService.addQuestion(q1);
        Assertions.assertEquals(q1, javaQuestionService.getRandomQuestion());
    }
}
