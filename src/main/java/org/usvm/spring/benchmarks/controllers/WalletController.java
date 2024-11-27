package org.usvm.spring.benchmarks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.usvm.spring.benchmarks.model.Wallet;
import org.usvm.spring.benchmarks.storage.WalletRepository;


@Controller
public class WalletController {
	private final WalletRepository wallets;

	public WalletController(WalletRepository walletRepository) {
		this.wallets = walletRepository;
	}

	@ModelAttribute("wallet")
	public Wallet findWallet(@PathVariable(name = "ownerId", required = false) Integer ownerId) {
		return ownerId == null ? new Wallet() : this.wallets.findById(ownerId);
	}
}
