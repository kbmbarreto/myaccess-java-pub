package br.com.lambdateam.myaccessjava.exceptions;

import org.springframework.http.HttpStatus;

public class ExpiredJwtException extends RuntimeException{
    public ExpiredJwtException(HttpStatus unauthorized, String message) {
        super(message);
    }
}