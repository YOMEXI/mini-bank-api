package com.example.bank.Exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorController {
    @ExceptionHandler(CustomApiException.class)
    ResponseEntity handleCustomApiException(Exception e){

        CustomApiException customErrorException = (CustomApiException) e;
        HttpStatus status = customErrorException.getStatus();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        customErrorException.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    ResponseEntity handleBindingErrors (MethodArgumentNotValidException exception){


        List errorList = exception.getFieldErrors().stream().map(fieldError -> {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

            return errorMap;
        }).collect(Collectors.toList());

        return  ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity handleConstraintViolationExceptionErrors (ConstraintViolationException exception){


        List errorList = exception.getConstraintViolations().stream().map(fieldError -> {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put((fieldError.getPropertyPath()).toString(), fieldError.getMessage());

            return errorMap;
        }).collect(Collectors.toList());

        return  ResponseEntity.badRequest().body(errorList);
    }

}
