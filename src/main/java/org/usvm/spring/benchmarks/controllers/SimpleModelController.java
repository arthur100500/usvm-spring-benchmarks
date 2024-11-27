package org.usvm.spring.benchmarks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.usvm.spring.benchmarks.model.Wallet;

import java.util.Map;

@Controller
public class SimpleModelController {
	// Model interactions
	@GetMapping("/wallets/addToModel")
	public void addToModel(Map<String, Object> model) {
		model.put("new_wallet", new Wallet());
	}

	@GetMapping("/wallets/passThroughModel")
	public String passThroughModel(Map<String, Object> model) {
		model.put("new_wallet", new Wallet());
		var wallet = model.get("new_wallet");
		return wallet.toString();
	}

	@GetMapping("/wallets/readFromModel")
	public String readFromModel(Map<String, Object> model) {
		return model.get("new_wallet").toString();
	}
}
