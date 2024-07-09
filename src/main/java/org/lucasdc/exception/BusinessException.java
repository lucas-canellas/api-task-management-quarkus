package org.lucasdc.exception;

import jakarta.ws.rs.ext.Provider;

@Provider
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
 }
