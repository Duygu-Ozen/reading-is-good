package com.readingIsGood.readingIsGood.api.response;


public class ErrorResponse {

    private boolean success;
    private String message;


    public ErrorResponse(boolean b, String message) {
        this.success = b;
        this.message = message;

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}