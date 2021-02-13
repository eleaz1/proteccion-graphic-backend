package com.abivan.graphics.service.errors;

public class GraphicException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String code;

    private final int responseCode;

    public GraphicException(String code, int responseCode, String message) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public String getCode() {
        return code;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
