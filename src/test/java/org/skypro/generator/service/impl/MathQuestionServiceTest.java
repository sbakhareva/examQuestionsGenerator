package org.skypro.generator.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.impl.MathQuestionRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    private MathQuestionRepository mathQuestionRepository;

    private Map<Long, Question> questions;

    private MathQuestionService mathQuestionService;

    @BeforeEach
    void setUp() {
        questions = Mockito.spy(new HashMap<>());
        mathQuestionRepository = new MathQuestionRepository(questions);
        mathQuestionService = new MathQuestionService(mathQuestionRepository);
    }

    @Test
    void addQuestion_WhenQuestionIsNotNull() {
        Question q1 = new Question("aaaaa", "oooooo");
        mathQuestionService.addQuestion(q1);
        System.out.println(questions);
        assertTrue(mathQuestionRepository.getAll().containsValue(q1));
    }

    @Test
    void addQuestion_WhenSameIsAlreadyInStorage() {
        Question q1 = new Question("3", "4");
        Question q2 = new Question("3", "4");
        mathQuestionService.addQuestion(q1);
        assertTrue(questions.containsValue(q1));
        assertThrows(IncorrectValueException.class, () -> mathQuestionService.addQuestion(q2));
        assertEquals(1, questions.size());
    }

    @Test
    void addQuestion_WhenValueIsEmpty() {
        Question q1 = new Question("    ", "answer");
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionService.addQuestion(q1));
    }
    @Test
    void removeQuestionTest() {
        Question q1 = new Question("1 + 1", "2");
        Question q2 = new Question("3 + 2", "5");
        mathQuestionService.addQuestion(q1);
        mathQuestionService.addQuestion(q2);
        mathQuestionService.removeQuestion("1");
        Assertions.assertEquals(1, questions.size());
        assertTrue(questions.toString().contains("3"));
    }

    @Test
    void getAllQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> mathQuestionService.getAllQuestions());
    }

    @Test
    void getAllQuestions_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaaaa", "ooooooooo");
        mathQuestionService.addQuestion(q1);
        assertTrue(questions.containsValue(q1));
        Assertions.assertFalse(mathQuestionService.getAllQuestions().isEmpty());
    }

    @Test
    void getRandomQuestion_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> mathQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("uuuuu", "aaaaaaaa");
        mathQuestionService.addQuestion(q1);
        when(mathQuestionService.getRandomQuestion()).thenReturn(q1);
        Assertions.assertEquals(q1, mathQuestionService.getRandomQuestion());
    }
}
