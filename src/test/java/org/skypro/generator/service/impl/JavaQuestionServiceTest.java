package org.skypro.generator.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.IncorrectValueException;
import org.skypro.generator.model.questionEx.JavaQuestion;
import org.skypro.generator.repository.JavaQuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository javaQuestionRepository;

    @InjectMocks
    private JavaQuestionServiceImpl javaQuestionService;

    @Test
    void addQuestion_WhenQuestionIsNotNull() {
        JavaQuestion q1 = new JavaQuestion("question", "answer");
        javaQuestionService.addQuestion(q1);
        verify(javaQuestionRepository).save(q1);
    }

    @Test
    void addQuestion_WhenSameIsAlreadyInStorage() {
        JavaQuestion question1 = new JavaQuestion("question", "answer");
        JavaQuestion question2 = new JavaQuestion("question", "answer");
        javaQuestionService.addQuestion(question1);
        when(javaQuestionRepository.findAll()).thenReturn(List.of(question1));
        verify(javaQuestionRepository).save(question1);
        assertThrows(IncorrectValueException.class, () -> javaQuestionService.addQuestion(question2));
    }

    @Test
    void addQuestion_WhenValueIsEmpty() {
        JavaQuestion question1 = new JavaQuestion("    ", "answer");
        Assertions.assertThrows(IncorrectValueException.class, () -> javaQuestionService.addQuestion(question1));
    }
    @Test
    void removeQuestionTest() {
        JavaQuestion q1 = new JavaQuestion("question", "answer");
        JavaQuestion q2 = new JavaQuestion("another", "one");
        when(javaQuestionRepository.findAll()).thenReturn(List.of(q1, q2));
        javaQuestionService.removeByTherm("question");
        verify(javaQuestionRepository).deleteByQuestionIgnoreCaseContains("question");
    }

    @Test
    void getAllQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> javaQuestionService.getAll());
    }

    @Test
    void getAllQuestions_WhenThereAreQuestionsInStorage() {
        JavaQuestion question1 = new JavaQuestion("question", "answer");
        when(javaQuestionRepository.findAll()).thenReturn(List.of(question1));
        assertTrue(javaQuestionService.getAll().contains(question1));
    }

    @Test
    void getRandomQuestion_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> javaQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_WhenThereAreQuestionsInStorage() {
        JavaQuestion q1 = new JavaQuestion("question", "answer");
        when(javaQuestionRepository.findAll()).thenReturn(List.of(q1));
        Assertions.assertEquals(q1, javaQuestionService.getRandomQuestion());
    }
}
