package org.skypro.generator.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.model.Question;
import org.skypro.generator.model.questionEx.JavaQuestion;
import org.skypro.generator.model.questionEx.MathQuestion;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionServiceImpl javaQuestionService;

    @Mock
    private MathQuestionServiceImpl mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;

    @Test
    void getQuestions_WhenThereAreQuestionsInStorage() {
        JavaQuestion question1 = new JavaQuestion("question", "answer");
        MathQuestion question2 = new MathQuestion("2 + 2", " = 4");
        when(javaQuestionService.getAll()).thenReturn(List.of(question1));
        when(mathQuestionService.getAll()).thenReturn(List.of(question2));
        assertThat(examinerServiceImpl.getQuestions(2)).isNotNull();
        assertEquals(2, examinerServiceImpl.getQuestions(2).size());
        assertTrue(examinerServiceImpl.getQuestions(2).contains(new Question("question", "answer")));
    }

    @Test
    void getQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> examinerServiceImpl.getQuestions(0));
    }

    @Test
    void getQuestions_WhenAmountIsTooBig() {
        JavaQuestion q1 = new JavaQuestion("aaaaaa", "ooooooo");
        when(javaQuestionService.getAll()).thenReturn(List.of(q1));
        Assertions.assertThrows(TooLargeNumberRequestedException.class, () -> examinerServiceImpl.getQuestions(3));
    }
}
