package org.skypro.generator.service;

import org.skypro.generator.model.Question;

import java.util.List;

public interface ExaminerService {

    List<Question> getQuestions(int amount);
}
