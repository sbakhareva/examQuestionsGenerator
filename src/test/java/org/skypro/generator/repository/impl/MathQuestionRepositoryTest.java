package org.skypro.generator.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exceptions.EmptyStorageException;
import org.skypro.generator.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionRepositoryTest {
    private HashMap<Long, Question> questions;

    private MathQuestionRepository mathQuestionRepository;

    @BeforeEach
    void setUp() {
        questions = new HashMap<>();
        mathQuestionRepository = new MathQuestionRepository(questions);
    }

    @Test
    void addQuestion() {
        Question q1 = new Question("aaaaa", "oooooo");
        mathQuestionRepository.addQuestion(q1);
        assertTrue(questions.containsValue(q1));
    }

    @Test
    void addQuestion_WhenQuestionOrAnswerIsBlank() {
        Question q1 = new Question("    ", "aaaaa");
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionRepository.addQuestion(q1));
        Question q2 = new Question("oooooo", "     ");
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionRepository.addQuestion(q2));
    }

    @Test
    void addQuestion_WhenSameIsAlreadyInStorage() {
        Question q1 = new Question("aaa", "ooo");
        Question q2 = new Question("aaa", "ooo");
        mathQuestionRepository.addQuestion(q1);
        assertTrue(questions.containsValue(q1));
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionRepository.addQuestion(q2));
    }

    @Test
    void removeQuestion() {
        Question q1 = new Question("aaa", "ooo");
        Question q2 = new Question("ooooo", "eeeee");
        mathQuestionRepository.addQuestion(q1);
        mathQuestionRepository.addQuestion(q2);
        Assertions.assertTrue(mathQuestionRepository.getAll().containsValue(q1));
        Assertions.assertTrue(mathQuestionRepository.getAll().containsValue(q2));
        mathQuestionRepository.remove("aaa");
        assertFalse(mathQuestionRepository.getAll().containsValue(q1));
        assertEquals(1, mathQuestionRepository.getAll().size());
    }

    @Test
    void removeQuestion_WhenThermIsBlank() {
        Question q1 = new Question("aaa", "ooo");
        mathQuestionRepository.addQuestion(q1);
        assertThrows(RuntimeException.class, () -> mathQuestionRepository.remove("    "));
    }

    @Test
    void getAll_WhenThereAreQuestionsInStorage() {
        Question q1 = new Question("aaa", "ooo");
        Question q2 = new Question("ooooo", "eeeee");
        mathQuestionRepository.addQuestion(q1);
        mathQuestionRepository.addQuestion(q2);
        assertEquals(2, mathQuestionRepository.getAll().size());
        assertTrue(mathQuestionRepository.getAll().containsValue(q1));
        assertTrue(mathQuestionRepository.getAll().containsValue(q2));
    }

    @Test
    void getAll_WhenStorageIsEmpty() {
        assertThrows(EmptyStorageException.class, () -> mathQuestionRepository.getAll());
    }
}