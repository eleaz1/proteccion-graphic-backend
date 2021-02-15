package com.abivan.graphics.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GraphicError {

    private final HttpStatus status;
    private final String description;

}
