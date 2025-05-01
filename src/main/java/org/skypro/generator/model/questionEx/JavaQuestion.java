package org.skypro.generator.model.questionEx;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NonNull;
import org.skypro.generator.model.Question;

@Entity(name = "java_questions")
public class JavaQuestion extends Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String question;
    @NonNull
    private String answer;
}
