package org.skypro.generator.model.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExamControllerAdvice {

    @ExceptionHandler(TooLargeNumberRequestedException.class)
    public ResponseEntity<String> handleTooLargeNumberRequestedException() {
        return ResponseEntity.badRequest().body("Запрошено слишком большое число.");
    }

    @ExceptionHandler(IncorrectValueException.class)
    public ResponseEntity<String> handleIncorrectValueException() {
        return ResponseEntity.badRequest().body("Вопрос или ответ не может быть пустым.");
    }

    @ExceptionHandler(EmptyStorageException.class)
    public ResponseEntity<String> handleEmptyStorageException() {
        return ResponseEntity.badRequest().body("В хранилище нет вопросов!");
    }
}
