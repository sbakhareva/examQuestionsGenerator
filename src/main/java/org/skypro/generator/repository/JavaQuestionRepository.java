package org.skypro.generator.repository;

import org.skypro.generator.model.questionEx.JavaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JavaQuestionRepository extends JpaRepository<JavaQuestion, Long> {

    void deleteByQuestionIgnoreCaseContains(String therm);

    Optional<JavaQuestion> findByQuestionIgnoreCaseContains(String question);
}
