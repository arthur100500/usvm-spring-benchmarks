package org.usvm.spring.benchmarks.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.benchmarks.model.Wallet;

import java.util.Map;

@RestController
public class SimpleModelController {
	// Model interactions
	@RequestMapping(value = "/wallets/addToModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addToModel(Map<String, Object> model) {
		model.put("new_wallet", new Wallet());
	}

	@RequestMapping(value = "/wallets/passThroughModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String passThroughModel(Map<String, Object> model) {
		model.put("new_wallet", new Wallet());
		var wallet = model.get("new_wallet");
		return wallet.toString();
	}

	@RequestMapping(value = "/wallets/readFromModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String readFromModel(Map<String, Object> model) {
		return model.get("new_wallet").toString();
	}
}
