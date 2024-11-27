package org.usvm.spring.benchmarks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {
	@Column(name = "number")
	@NotBlank
	@Digits(fraction = 0, integer = 16)
	private String number;

	@Column(name = "expiry_date")
	@NotBlank
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate expiryDate;

	@Column(name = "pin")
	@NotBlank
	@Digits(fraction = 0, integer = 3)
	private String pin;
}
