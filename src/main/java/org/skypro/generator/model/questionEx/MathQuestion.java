package org.skypro.generator.model.questionEx;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.skypro.generator.model.Question;


@Entity(name = "math_questions")
public class MathQuestion extends Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
}
