//package org.skypro.generator.service.impl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.skypro.generator.exceptions.EmptyStorageException;
//import org.skypro.generator.exceptions.TooLargeNumberRequestedException;
//import org.skypro.generator.model.Question;
//import org.skypro.generator.repository.JavaQuestionRepository;
//import org.skypro.generator.repository.MathQuestionRepository;
//
//import java.util.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ExaminerServiceImplTest {
//
//    @Spy
//    private HashMap<Long, Question> questions;
//
//    @Mock
//    private JavaQuestionRepository javaQuestionRepository;
//
//    @Mock
//    private MathQuestionRepository mathQuestionRepository;
//
//    @Mock
//    private JavaQuestionService javaQuestionService;
//
//    @Mock
//    private MathQuestionService mathQuestionService;
//
//    @InjectMocks
//    private ExaminerServiceImpl examinerServiceImpl;
//
//    @Test
//    void getQuestions_WhenThereAreQuestionsInStorage() {
//        Question q1 = new Question("aaaaa", "oooooo");
//        Question q2 = new Question(" 2 + 2", " = 4");
//        javaQuestionService.addQuestion(q1);
//        mathQuestionService.addQuestion(q2);
//        when(javaQuestionService.getAllQuestions()).thenReturn(Map.of(1L, q1));
//        when(mathQuestionService.getAllQuestions()).thenReturn(Map.of(2L, q2));
//        Assertions.assertEquals(2, examinerServiceImpl.getExamQuestions().size());
//        Assertions.assertTrue(examinerServiceImpl.getExamQuestions().containsValue(q1));
//        Assertions.assertTrue(examinerServiceImpl.getExamQuestions().containsValue(q2));
//    }
//
//    @Test
//    void getQuestions_WhenStorageIsEmpty() {
//        Assertions.assertThrows(EmptyStorageException.class, () -> examinerServiceImpl.getQuestions(0));
//    }
//
//    @Test
//    void getQuestions_WhenAmountIsTooBig() {
//        Question q1 = new Question("aaaaaa", "ooooooo");
//        when(javaQuestionService.getAllQuestions()).thenReturn(Map.of(1L, q1));
//        Assertions.assertThrows(TooLargeNumberRequestedException.class, () -> examinerServiceImpl.getQuestions(3));
//    }
//
//    @Test
//    void getQuestions_WhenAmountIsCorrect() {
//        Question q1 = new Question("aaaaaa", "oooooo");
//        Question q2 = new Question(" 2 + 2", " = 4");
//        when(javaQuestionService.getAllQuestions()).thenReturn(Map.of(1L, q1));
//        when(mathQuestionService.getAllQuestions()).thenReturn(Map.of(2L, q2));
//        Assertions.assertTrue(examinerServiceImpl.getQuestions(2).contains(q1));
//    }
//}
