package com.abivan.graphics.service.exception;

import lombok.Getter;

@Getter
public class GraphicException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final transient GraphicError graphicError;

    public GraphicException(String message, GraphicError graphicError) {
        super(message);
        this.graphicError = graphicError;
    }
}
