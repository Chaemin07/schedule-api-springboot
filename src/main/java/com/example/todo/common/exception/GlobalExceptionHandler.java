package com.example.todo.common.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleLoginError() {
        return "";
    }

    // @Valid의 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidError() {
        return "";
    }

    // @Validated에서의 오류
//    @ExceptionHandler(ConstraintViolationException.class)


    // 필드 타입 불일치 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleTypeError() {

        return "";
    }

    // 파라미터 변수의 타입 불일치 오류
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleParamTypeError() {
        return "";
    }
}





