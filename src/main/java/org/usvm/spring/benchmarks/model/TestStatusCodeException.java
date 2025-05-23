package org.usvm.spring.benchmarks.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TestStatusCodeException extends Exception {
    public TestStatusCodeException(String message) {
        super("Test SC Exception Prefix" + message);
    }
}
