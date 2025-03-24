package org.usvm.spring.benchmarks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet extends BaseEntity {
	@Column(name = "cash")
	@NotBlank
	private String cash;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	@OrderBy("number")
	private List<Card> cards;

	// For json tests
	private Card card;

	@Override
	public String toString() {
		// TODO: Add proper card render
		var cardsFormatted = "[cards]";
		return String.format("Wallet %s %s", cash, cardsFormatted);
	}

	public Integer getCash(){
		return Integer.parseInt(cash);
	}
}
