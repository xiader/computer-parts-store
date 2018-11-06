package com.gmail.sasha.myproject.config.handlers;

import com.gmail.sasha.myproject.web.util.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    @Qualifier("pageProperties")
    private PageProperties pageProperties;


    @ExceptionHandler(javax.persistence.PersistenceException.class)
    public ResponseEntity<?> handleDbException() {
        return new ResponseEntity<>(
                "Informing you, that your db layer is bad", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException() {
        return new ResponseEntity<>(
                "No no no, you have no power here", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
