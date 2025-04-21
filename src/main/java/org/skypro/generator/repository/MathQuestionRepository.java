package org.skypro.generator.repository;

import org.skypro.generator.model.questionEx.MathQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface MathQuestionRepository extends JpaRepository<MathQuestion, Long> {

    void deleteByQuestionIgnoreCaseContains(String therm);

    Optional<MathQuestion> findByQuestionIgnoreCaseContains(String question);
}
