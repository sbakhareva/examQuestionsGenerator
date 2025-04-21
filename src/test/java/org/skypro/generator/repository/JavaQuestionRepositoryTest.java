package org.skypro.generator.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.model.exceptions.EmptyStorageException;
import org.skypro.generator.model.exceptions.IncorrectValueException;
import org.skypro.generator.model.Question;
import org.skypro.generator.model.questionEx.JavaQuestion;
import org.skypro.generator.repository.JavaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@ExtendWith(MockitoExtension.class)
class JavaQuestionRepositoryTest {

    @Autowired
    private JavaQuestionRepository javaQuestionRepository;


    @Test
    void addQuestion() {
        JavaQuestion question = new JavaQuestion("aaaaa", "oooooo");
        javaQuestionRepository.save(question);
        assertTrue(javaQuestionRepository.findAll().contains(question));
    }
}