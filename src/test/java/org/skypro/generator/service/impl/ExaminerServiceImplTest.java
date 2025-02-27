package org.skypro.generator.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.model.Question;
import org.skypro.generator.repository.impl.JavaQuestionRepository;
import org.skypro.generator.repository.impl.MathQuestionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private MathQuestionService mathQuestionService;
    @Mock
    private JavaQuestionService javaQuestionService;
    @Spy
    private HashSet<Question> questions;
    @InjectMocks
    ExaminerServiceImpl examinerServiceImpl;

    @BeforeEach
    void setUp() {
        questions = Mockito.spy(new HashSet<>());
    }
    @Test
    void getQuestions_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaaaa", "oooooo");
        when(javaQuestionService.getAllQuestions()).thenReturn(Set.of(q1));
        Assertions.assertTrue(examinerServiceImpl.getExamQuestions().contains(q1));
        System.out.println(examinerServiceImpl.getExamQuestions());
    }

    @Test
    void getQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> examinerServiceImpl.getQuestions(0));
    }

    @Test
    void getQuestions_WhenAmountIsTooBig() {
        Question q1 = new Question("aaaaaa", "ooooooo");
        Question q2 = new Question("2 + 2", " = 4");
        when(javaQuestionService.getAllQuestions()).thenReturn(Set.of(q1));
        when(mathQuestionService.getAllQuestions()).thenReturn(Set.of(q2));
        Assertions.assertThrows(TooLargeNumberRequestedException.class, () -> examinerServiceImpl.getQuestions(3));
    }

    @Test
    void getQuestions_WhenAmountIsCorrect() {
        Question q1 = new Question("1", "2");
        Question q2 = new Question("3", "4");
        when(javaQuestionService.getAllQuestions()).thenReturn(Set.of(q1));
        when(mathQuestionService.getAllQuestions()).thenReturn(Set.of(q2));
        Assertions.assertTrue(examinerServiceImpl.getQuestions(2).contains(q1));
        Assertions.assertTrue(examinerServiceImpl.getQuestions(2).contains(q2));
    }
}
