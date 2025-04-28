package org.usvm.spring.benchmarks.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.benchmarks.model.GraphNode;
import org.usvm.spring.benchmarks.model.TestJsonPayload;
import org.usvm.spring.benchmarks.model.TestJsonPayloadWithCtor;
import org.usvm.spring.benchmarks.model.Wallet;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

@RestController
public class BodyController {
    private void emptyMethod() {

    }

    @RequestMapping(value = "/body/body_with_validation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String bodyWithValidation(@Valid @RequestBody Wallet wallet, Errors errors) {
        if (Objects.equals(wallet.getCash(), 123))
            throw new IllegalArgumentException("Wallet cash cant be 123");

        emptyMethod();
        return wallet.getId().toString();
    }

    @RequestMapping(value = "/body/graph", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String bodyGraph(@RequestBody GraphNode g) {
        return String.format("Value %d", g.value);
    }

    private void mutate(TestJsonPayload payload) {
        for (int i = 0; i < Array.getLength(payload.objectArray); i++)
            payload.objectArray[i] = null;

        payload.objectList.replaceAll(null);
    }

    @RequestMapping(value = "/body/object", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String bodyObject(@RequestBody TestJsonPayload payload) {

        if (payload.stringValue.equals("prohibited"))
            return "stringValue = prohibited";
        if (payload.intValue == 15)
            return "intValue = 15";
        if (payload.ignoredProperty == 1)
            return "ignoredProperty = 15";
        if (payload.includedProperty == 3)
            return "includedProperty = 3";
        if (payload.objectArray[0].second == 13) {
            mutate(payload);
            return "objectArray[0].second = 13";
        }
        if (payload.objectArrayArray[0][1].second == 15) {
            mutate(payload);
            return "objectArray[0][1].second = 15";
        }
        if (payload.stringArray[1].equals("hi!"))
            return "stringArray[1] = hi!";
        if (payload.stringArrayArray[1][0].equals("hello!"))
            return "stringArrayArray[1][0] = hello!";
        if (payload.recursiveObjectValue.left.left.value == 14)
            return "recursiveObjectValue.left.left.value = 14";
        if (payload.beanWithCtor.first == 22)
            return "beanWithCtor.first = 22";
        if (payload.beanWithCtor.second == 33 && payload.beanWithCtor.first == 13)
            return "beanWithCtor.second = 33 (-> 20)";
        return "none of the predicates matched";
    }

    @RequestMapping(value = "/body/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String bodyList(@RequestBody List<GraphNode> payload) {
        if (payload.get(1).value == 3)
            return "value == 3";
        return "value != 3";
    }
}
