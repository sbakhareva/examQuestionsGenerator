package org.skypro.generator.repository;

import org.skypro.generator.model.Question;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.Position;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    //void deleteQuestionByQuestion(String question);
}
