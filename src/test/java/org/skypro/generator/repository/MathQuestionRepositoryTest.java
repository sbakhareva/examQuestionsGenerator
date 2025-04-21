package org.skypro.generator.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.model.exceptions.IncorrectValueException;
import org.skypro.generator.model.questionEx.MathQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@ExtendWith(MockitoExtension.class)
class MathQuestionRepositoryTest {

    @Autowired
    private MathQuestionRepository mathQuestionRepository;

    @Test
    void addQuestion() {
        MathQuestion q1 = new MathQuestion("question", "answer");
        mathQuestionRepository.save(q1);
        assertTrue(mathQuestionRepository.findAll().contains(q1));
    }

    @Test
    void addQuestion_WhenQuestionOrAnswerIsBlank() {
        MathQuestion question1 = new MathQuestion("    ", "aaaaa");
        MathQuestion question2 = new MathQuestion("oooooo", "     ");
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionRepository.save(question1));
        Assertions.assertThrows(IncorrectValueException.class, () -> mathQuestionRepository.save(question2));
    }
}