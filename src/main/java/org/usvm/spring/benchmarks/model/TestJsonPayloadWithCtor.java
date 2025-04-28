package org.usvm.spring.benchmarks.model;

public class TestJsonPayloadWithCtor {
    public int first;
    public int second;
    
    public TestJsonPayloadWithCtor(int first, int second) {
        this.first = first;
        this.second = second + first;
    }
}
