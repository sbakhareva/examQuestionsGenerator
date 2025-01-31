package org.skypro.generator.service;
import org.skypro.generator.model.Question;
import java.util.Set;

public interface ExaminerService {

    Set<Question> getQuestions(int amount);
}
