package org.usvm.spring.benchmarks.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.benchmarks.model.Wallet;

import javax.xml.transform.Result;
import java.security.Principal;
import java.util.Objects;

@RestController
public class OtherResolverControllers {

    @RequestMapping(value = "/other/http_entity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<String> httpEntity(HttpEntity<Wallet> requestEntity) {
        if (requestEntity.getBody() == null)
            throw new IllegalArgumentException("Wallet cant be null");

        if (Objects.equals(requestEntity.getBody().getCash(), 123))
            throw new IllegalArgumentException("Wallet cash cant be 123");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Wallet_ID", requestEntity.getBody().getId().toString());
        return new HttpEntity<>("Wallet cash: " + requestEntity.getBody().getCash(), responseHeaders);
    }

    @RequestMapping(value = "/other/session_write", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sessionWriteController(HttpSession session) {
        session.setAttribute("Attribute", "Hi!");
        
        return "ok";
    }

    @RequestMapping(value = "/other/session_read", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sessionController(HttpSession session) {

        return (String)session.getAttribute("Attribute");
    }

    @RequestMapping(value = "/other/principal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String userPrincipal(Principal principal) {

        return principal.getName();
    }

    @RequestMapping(value = "/other/authentication", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String userAuthentication(Authentication authentication) {

        return authentication.name();
    }
    
}
