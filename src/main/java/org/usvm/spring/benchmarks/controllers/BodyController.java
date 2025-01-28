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
import org.usvm.spring.benchmarks.model.Wallet;

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
    public String bodyInterfaceList(@RequestBody GraphNode g) {

        return String.format("Value %d", g.value);
    }
    
    @RequestMapping(value = "/body/interface_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String bodyInterfaceList(@RequestBody List<ISampleInterface> lst) {

        return lst.get(0).send("123");
    }
    
    private interface ISampleInterface {
        String send(String message);
    }
}
