package org.skypro.generator.repository;

import org.skypro.generator.model.Question;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public interface QuestionRepository {

    void addQuestion(Question question);

    void remove(Question question);

    Set<Question> getAll();
}
