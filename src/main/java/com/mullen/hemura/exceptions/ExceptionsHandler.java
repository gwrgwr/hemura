package com.mullen.hemura.exceptions;

import com.mullen.hemura.exceptions.dto.ExceptionsHandlerDTO;
import com.mullen.hemura.exceptions.session.SessionNotFoundException;
import com.mullen.hemura.exceptions.session.SessionReachedLimitException;
import com.mullen.hemura.exceptions.task.TaskNotFoundException;
import com.mullen.hemura.exceptions.user.UserAlreadyExistsException;
import com.mullen.hemura.exceptions.user.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionsHandlerDTO> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionsHandlerDTO(request.getRequestURI(), HttpStatus.NOT_FOUND, "User Not Found", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionsHandlerDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionsHandlerDTO(request.getRequestURI(), HttpStatus.CONFLICT, "User Already Exists", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ExceptionsHandlerDTO> handleSessionNotFoundException(SessionNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionsHandlerDTO(request.getRequestURI(), HttpStatus.NOT_FOUND, "Session Not Found", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(SessionReachedLimitException.class)
    public ResponseEntity<ExceptionsHandlerDTO> handleSessionReachedLimitException(SessionReachedLimitException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionsHandlerDTO(request.getRequestURI(), HttpStatus.CONFLICT, "Session Reached Limit", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionsHandlerDTO> handleTaskNotFoundException(TaskNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionsHandlerDTO(request.getRequestURI(), HttpStatus.NOT_FOUND, "Task Not Found", e.getMessage(), LocalDateTime.now()));
    }
}
