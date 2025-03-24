package org.usvm.spring.benchmarks.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
    public String bodyGraph(@RequestBody GraphNode g) {

		if (g.value > 10)
			return "Value of root node is greater than 10";

		if (g.right == null)
			return "Right node does not exist";

		if (g.right.left == null)
			return "Right left node does not exist";

		if (g.left.value > 10)
			return "Value of left node is greater than 10";

		if (g.left.left.left.left.value > 10)
			return "Value of 4x left node is greater than 10";

		if (g.left.left.right.left.value > 10)
			return "Value of 2x left right left node is greater than 10";

		return "None of the conditions matched";
    }

    @RequestMapping(value = "/body/interface_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String bodyInterfaceList(@RequestBody List<ISampleInterface> lst) {

        return lst.get(0).send("123");
    }

    private interface ISampleInterface {
        String send(String message);
    }
}
