package com.readingIsGood.readingIsGood.api.handler;

import com.readingIsGood.readingIsGood.api.response.ErrorResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    private static final Log logger = LogFactory.getLog(ErrorHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse argumentError(IllegalArgumentException ex) {
        logger.error(String.format("%s : %s", HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
        return new ErrorResponse(false, ex.getMessage());

    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse argumentError(NoSuchElementException ex) {
        logger.error(String.format("%s : %s", HttpStatus.NOT_FOUND.name(), ex.getMessage()));
        return new ErrorResponse(false, ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse argumentError(Exception ex) {
        ex.printStackTrace();
        logger.error(String.format("%s : %s", HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage()));
        return new ErrorResponse(false, ex.getMessage());

    }

}
