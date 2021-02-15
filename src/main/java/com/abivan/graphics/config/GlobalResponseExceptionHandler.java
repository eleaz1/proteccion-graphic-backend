package com.abivan.graphics.config;

import com.abivan.graphics.service.exception.GraphicError;
import com.abivan.graphics.service.exception.GraphicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = MessageFormat.format("{0} el parametro no esta:", ex.getParameterName());
        log.error(ex.getLocalizedMessage());
        return buildResponseEntity(new GraphicError(status, error));
    }

    @ExceptionHandler(GraphicException.class)
    protected ResponseEntity<Object> handleGraphicException(GraphicException ex) {
        log.error(ex.getLocalizedMessage());
        return buildResponseEntity(ex.getGraphicError());
    }

    private ResponseEntity<Object> buildResponseEntity(GraphicError graphicError) {
        return new ResponseEntity<>(graphicError, graphicError.getStatus());
    }
}
