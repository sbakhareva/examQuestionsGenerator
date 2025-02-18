package org.skypro.generator;

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
import org.skypro.generator.exceptions.TooLargeNumberRequestedException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.ExaminerServiceImpl;
import org.skypro.generator.service.QuestionService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @Spy
    private HashSet<Question> questions;

    @InjectMocks
    ExaminerServiceImpl examinerServiceImpl;

    @BeforeEach
    void setUp() {
        questions = Mockito.spy(new HashSet<>());
        examinerServiceImpl = new ExaminerServiceImpl(questionService, questions);
    }

    @Test
    void getQuestions_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaaaa", "oooooo");
        when(questionService.getAllQuestions()).thenReturn(Set.of(q1));
        Assertions.assertTrue(examinerServiceImpl.getQuestions(1).contains(q1));
    }

    @Test
    void getQuestions_WhenStorageIsEmpty() {
        Assertions.assertThrows(EmptyStorageException.class, () -> examinerServiceImpl.getQuestions(0));
    }

    @Test
    void getQuestions_WhenAmountIsTooBig() {
        Question q1 = new Question("aaaaaa", "ooooooo");
        when(questionService.getAllQuestions()).thenReturn(Set.of(q1));
        Assertions.assertThrows(TooLargeNumberRequestedException.class, () -> examinerServiceImpl.getQuestions(2));
    }

    @Test
    void getQuestions_WhenAmountIsCorrect() {
        Question q1 = new Question("1", "2");
        Question q2 = new Question("3", "4");
        when(questionService.getAllQuestions()).thenReturn(Set.of(q1, q2));
        Assertions.assertTrue(examinerServiceImpl.getQuestions(2).contains(q1));
        Assertions.assertTrue(examinerServiceImpl.getQuestions(2).contains(q2));
    }
}
