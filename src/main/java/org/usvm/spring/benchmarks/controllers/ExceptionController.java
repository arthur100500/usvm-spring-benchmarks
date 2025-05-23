package org.usvm.spring.benchmarks.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.benchmarks.model.TestException;
import org.usvm.spring.benchmarks.model.TestStatusCodeException;

@RestController
public class ExceptionController {

    @RequestMapping(value = "/exception/throw", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String throwException() throws Exception {
        throw new TestException("among");
    }

    @RequestMapping(value = "/exception/conditional", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String conditionalException(@RequestParam String param) throws Exception {
        if (param.equals("123")) {
            return "param == 123";
        }
        throw new TestException("among");
    }

    @RequestMapping(value = "/exception/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String throwsResponseStatusException() throws Exception {
        throw new TestStatusCodeException("among");
    }
}
