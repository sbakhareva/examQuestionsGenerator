package org.skypro.generator.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.impl.JavaQuestionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private Set<Question> questions;

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        questions = Mockito.spy(new HashSet<>());
        JavaQuestionRepository javaQuestionRepository = new JavaQuestionRepository(questions);
        javaQuestionService = new JavaQuestionService(javaQuestionRepository);
    }
    @Test
    void addQuestion_WhenQuestionIsNotNull() {
        Question q1 = new Question("aaaaa", "oooooo");
        javaQuestionService.addQuestion(q1);
        verify(questions, times(1)).add(q1);
    }

    @Test
    void addQuestion_WhenSameIsAlreadyInStorage() {
        Question q1 = new Question("3", "4");
        Question q2 = new Question("3", "4");
        javaQuestionService.addQuestion(q1);
        verify(questions, times(1)).add(q1);
        javaQuestionService.addQuestion(q2);
        assertEquals(1, questions.size());
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
        Assertions.assertEquals(1, javaQuestionService.getAllQuestions().size());
        Assertions.assertFalse(questions.toString().contains("1"));
        Assertions.assertTrue(questions.toString().contains("3"));

    }

    @Test
    void getAllQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> javaQuestionService.getAllQuestions());
    }

    @Test
    void getAllQuestions_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaaaa", "ooooooooo");
        javaQuestionService.addQuestion(q1);
        Mockito.verify(questions).add(q1);
        Assertions.assertTrue(questions.contains(q1));
        Assertions.assertFalse(questions.isEmpty());
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
