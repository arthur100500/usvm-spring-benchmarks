package org.usvm.spring.benchmarks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


public class TestJsonPayload {
    public int intValue;
    public Integer integerValue;
    public String stringValue;
    public OurPoint objectValue;
    public GraphNode recursiveObjectValue;

    @JsonIgnore
    public int ignoredProperty;

    @JsonInclude
    public int includedProperty;

    public TestJsonPayloadWithCtor beanWithCtor;

    public List<String> stringList;
    public List<List<String>> stringListList;

    public String[] stringArray;
    public String[][] stringArrayArray;

    public List<TestJsonPayloadWithCtor> objectList;
    public List<List<TestJsonPayloadWithCtor>> objectListList;

    public TestJsonPayloadWithCtor[] objectArray;
    public TestJsonPayloadWithCtor[][] objectArrayArray;

}
