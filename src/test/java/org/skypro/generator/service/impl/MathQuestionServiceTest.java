package org.skypro.generator.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.IncorrectValueException;
import org.skypro.generator.model.questionEx.MathQuestion;
import org.skypro.generator.repository.MathQuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {


    @Mock
    private MathQuestionRepository mathQuestionRepository;

    @InjectMocks
    private MathQuestionServiceImpl mathQuestionService;

    @Test
    void addQuestion_WhenQuestionIsNotNull() {
        MathQuestion q1 = new MathQuestion("question", "answer");
        mathQuestionService.addQuestion(q1);
        verify(mathQuestionRepository).save(q1);
    }

    @Test
    void addQuestion_WhenSameIsAlreadyInStorage() {
        MathQuestion question1 = new MathQuestion("question", "answer");
        MathQuestion question2 = new MathQuestion("question", "answer");
        mathQuestionService.addQuestion(question1);
        when(mathQuestionRepository.findAll()).thenReturn(List.of(question1));
        verify(mathQuestionRepository).save(question1);
        assertThrows(IncorrectValueException.class, () -> mathQuestionService.addQuestion(question2));
    }

    @Test
    void addQuestion_WhenValueIsEmpty() {
        MathQuestion question1 = new MathQuestion("    ", "answer");
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionService.addQuestion(question1));
    }
    @Test
    void removeQuestionTest() {
        MathQuestion q1 = new MathQuestion("question", "answer");
        MathQuestion q2 = new MathQuestion("another", "one");
        when(mathQuestionRepository.findAll()).thenReturn(List.of(q1, q2));
        mathQuestionService.removeByTherm("question");
        verify(mathQuestionRepository).deleteByQuestionIgnoreCaseContains("question");
    }

    @Test
    void getAllQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> mathQuestionService.getAll());
    }

    @Test
    void getAllQuestions_WhenThereAreQuestionsInStorage() {
        MathQuestion question1 = new MathQuestion("question", "answer");
        when(mathQuestionRepository.findAll()).thenReturn(List.of(question1));
        assertTrue(mathQuestionService.getAll().contains(question1));
    }

    @Test
    void getRandomQuestion_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> mathQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_WhenThereAreQuestionsInStorage() {
        MathQuestion q1 = new MathQuestion("question", "answer");
        when(mathQuestionRepository.findAll()).thenReturn(List.of(q1));
        Assertions.assertEquals(q1, mathQuestionService.getRandomQuestion());
    }
}
