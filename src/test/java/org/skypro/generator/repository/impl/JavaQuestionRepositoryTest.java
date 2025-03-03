package org.skypro.generator.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;

import java.util.EmptyStackException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JavaQuestionRepositoryTest {

    private HashMap<Long, Question> questions;

    private JavaQuestionRepository javaQuestionRepository;

    @BeforeEach
    void setUp() {
        questions = new HashMap<>();
        javaQuestionRepository = new JavaQuestionRepository(questions);
    }

    @Test
    void addQuestion() {
        Question q1 = new Question("aaaaa", "oooooo");
        javaQuestionRepository.addQuestion(q1);
        assertTrue(questions.containsValue(q1));
    }

    @Test
    void addQuestion_WhenQuestionOrAnswerIsBlank() {
        Question q1 = new Question("    ", "aaaaa");
        Assertions.assertThrows(IncorrectValueException.class, () -> javaQuestionRepository.addQuestion(q1));
        Question q2 = new Question("oooooo", "     ");
        Assertions.assertThrows(IncorrectValueException.class, () -> javaQuestionRepository.addQuestion(q2));
    }

    @Test
    void addQuestion_WhenSameIsAlreadyInStorage() {
        Question q1 = new Question("aaa", "ooo");
        Question q2 = new Question("aaa", "ooo");
        javaQuestionRepository.addQuestion(q1);
        assertTrue(questions.containsValue(q1));
        Assertions.assertThrows(IncorrectValueException.class, () -> javaQuestionRepository.addQuestion(q2));
    }

    @Test
    void removeQuestion() {
        Question q1 = new Question("aaa", "ooo");
        Question q2 = new Question("ooooo", "eeeee");
        javaQuestionRepository.addQuestion(q1);
        javaQuestionRepository.addQuestion(q2);
        Assertions.assertTrue(javaQuestionRepository.getAll().containsValue(q1));
        Assertions.assertTrue(javaQuestionRepository.getAll().containsValue(q2));
        javaQuestionRepository.remove("aaa");
        assertFalse(javaQuestionRepository.getAll().containsValue(q1));
        assertEquals(1, javaQuestionRepository.getAll().size());
    }

    @Test
    void removeQuestion_WhenThermIsBlank() {
        Question q1 = new Question("aaa", "ooo");
        javaQuestionRepository.addQuestion(q1);
        assertThrows(RuntimeException.class, () -> javaQuestionRepository.remove("    "));
    }

    @Test
    void getAll_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaa", "ooo");
        Question q2 = new Question("ooooo", "eeeee");
        javaQuestionRepository.addQuestion(q1);
        javaQuestionRepository.addQuestion(q2);
        assertEquals(2, javaQuestionRepository.getAll().size());
        assertTrue(javaQuestionRepository.getAll().containsValue(q1));
        assertTrue(javaQuestionRepository.getAll().containsValue(q2));
    }

    @Test
    void getAll_WhenStorageIsEmpty() {
        assertThrows(EmptyStorageException.class, () -> javaQuestionRepository.getAll());
    }
}