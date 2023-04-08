package com.rest.api.errors;

import com.rest.api.utils.response.BaseRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseRepository> handlerResourceNotFound(ResourceNotFoundException ex){
        BaseRepository baseRepository =
                BaseRepository.builder()
                        .code(String.format(HttpStatus.NOT_FOUND.toString()))
                        .data(ex.getLocalizedMessage())
                        .success(false)
                        .build();
        return new ResponseEntity<>(baseRepository, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .stream()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();

                    errors.put(fieldName,message);
                });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handlerMethodArgumentException (MethodArgumentNotValidException ex){
//        Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(error -> {
//                    String fieldName = ((FieldError) error).getField();
//                    String message = error.getDefaultMessage();
//
//                    errors.put(fieldName,message);
//                });
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//    }
}
