package org.usvm.spring.benchmarks.model;

public class TestException extends Exception {
    public TestException(String message) {
        super("Test Exception Prefix" + message);
    }
}
