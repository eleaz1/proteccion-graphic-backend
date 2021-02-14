package com.abivan.graphics.service.errors;

public class GraphicException extends Exception {

    private static final long serialVersionUID = 1L;


    private final int responseCode;

    public GraphicException( int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
