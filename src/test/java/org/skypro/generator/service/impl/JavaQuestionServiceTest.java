//package org.skypro.generator.service.impl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.skypro.generator.exceptions.EmptyStorageException;
//import org.skypro.generator.exceptions.IncorrectValueException;
//import org.skypro.generator.model.Question;
//import org.skypro.generator.repository.QuestionRepository;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class JavaQuestionServiceTest {
//
//    @Mock
//    private QuestionRepository questionRepository;
//
//    @InjectMocks
//    private QuestionServiceImpl questionServiceImpl;
//
//    @Test
//    void addQuestion_WhenQuestionIsNotNull() {
//        Question q1 = new Question("aaaaa", "oooooo");
//        questionServiceImpl.addQuestion(q1);
//        Mockito.verify(questionRepository).save(q1);
//        assertTrue(questionRepository.findAll().contains(q1));
//    }
//
//    @Test
//    void addQuestion_WhenSameIsAlreadyInStorage() {
//        Question q1 = new Question("3", "4");
//        Question q2 = new Question("3", "4");
//        questionServiceImpl.addQuestion(q1);
//        assertTrue(questionRepository.findAll().contains(q1));
//        assertThrows(IncorrectValueException.class, () -> questionServiceImpl.addQuestion(q2));
//        assertEquals(1, questionRepository.findAll().size());
//    }
//
//    @Test
//    void addQuestion_WhenValueIsEmpty() {
//        Question q1 = new Question("    ", "answer");
//        Assertions.assertThrows(IncorrectValueException.class, () -> questionServiceImpl.addQuestion(q1));
//    }
//    @Test
//    void removeQuestionTest() {
//        Question q1 = new Question("1", "2");
//        Question q2 = new Question("3", "4");
//        questionServiceImpl.addQuestion(q1);
//        questionServiceImpl.addQuestion(q2);
//        questionServiceImpl.removeQuestion("1");
//        Assertions.assertEquals(1, questionRepository.findAll().size());
//        assertTrue(questionRepository.findAll().toString().contains("3"));
//    }
//
//    @Test
//    void getAllQuestions_WhenStorageIsEmpty() {
//        Assertions.assertThrows(EmptyStorageException.class, () -> questionServiceImpl.getAllQuestions());
//    }
//
//    @Test
//    void getAllQuestions_WhenThereAreQuestionsInStorage() {
//        Question q1 = new Question("aaaaa", "ooooooooo");
//        questionServiceImpl.addQuestion(q1);
//        assertTrue(questionRepository.findAll().contains(q1));
//        Assertions.assertFalse(questionServiceImpl.getAllQuestions().isEmpty());
//    }
//
//    @Test
//    void getRandomQuestion_WhenStorageIsEmpty() {
//        Assertions.assertThrows(EmptyStorageException.class, () -> questionServiceImpl.getRandomQuestion());
//    }
//
//    @Test
//    void getRandomQuestion_WhenThereAreQuestionsInStorage() {
//        Question q1 = new Question("uuuuu", "aaaaaaaa");
//        questionServiceImpl.addQuestion(q1);
//        when(questionServiceImpl.getRandomQuestion()).thenReturn(q1);
//        Assertions.assertEquals(q1, questionServiceImpl.getRandomQuestion());
//    }
//}
