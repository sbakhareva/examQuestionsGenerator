package org.skypro.generator.model.questionEx;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MathQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    public MathQuestion() {
    }

    public MathQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
