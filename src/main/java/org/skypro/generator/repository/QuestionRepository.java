package org.skypro.generator.repository;

import org.skypro.generator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

//    @Query()
//    void deleteByQuestion(String therm);
}
