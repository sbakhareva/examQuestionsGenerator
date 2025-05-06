package org.skypro.generator.model.questionEx;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.skypro.generator.model.Question;
import org.springframework.web.bind.annotation.RequestParam;


@Entity(name = "math_questions")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MathQuestion extends Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NonNull
    private String question;
    @NonNull
    private String answer;
}
