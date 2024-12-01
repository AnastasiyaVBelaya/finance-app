package by.it_academy.jd2.advice;

import by.it_academy.jd2.error.Error;
import by.it_academy.jd2.error.ErrorMessage;
import by.it_academy.jd2.dto.ErrorResponse;
import by.it_academy.jd2.dto.StructuredErrorResponse;
import by.it_academy.jd2.exception.*;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(EmailAlreadyExistsException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VerificationCodeExpiredException.class)
    public ResponseEntity<ErrorResponse> handle(VerificationCodeExpiredException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VerificationCodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(VerificationCodeNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CurrencyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(CurrencyAlreadyExistsException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationCategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(OperationCategoryAlreadyExistsException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handle(OptimisticLockingFailureException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<List<ErrorResponse>> handle(IllegalArgumentException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(error);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StructuredErrorResponse> handle(MethodArgumentNotValidException ex) {
        List<StructuredErrorResponse.ErrorDetailDTO> errorDetails = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            StructuredErrorResponse.ErrorDetailDTO errorDetail = StructuredErrorResponse.ErrorDetailDTO.builder()
                    .message(fieldError.getDefaultMessage())
                    .field(fieldError.getField())
                    .build();
            errorDetails.add(errorDetail);
        });

        StructuredErrorResponse structuredError = StructuredErrorResponse.builder()
                .logref(Error.STRUCTURED_ERROR.getValue())
                .errors(errorDetails)
                .build();

        return new ResponseEntity<>(structuredError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handle(AuthenticationException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ErrorMessage.AUTH_ERROR.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handle(LoginFailedException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handle(AccessDeniedException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ErrorMessage.ACCESS_DENIED.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorResponse>> handle(Exception ex) {

        ErrorResponse error = ErrorResponse.builder()
                .logref(Error.ERROR.getValue())
                .message(ex.getMessage())
                .build();
        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(error);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//ErrorMessage.GENERAL_ERROR