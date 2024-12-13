package org.usvm.spring.benchmarks.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.usvm.spring.benchmarks.model.Wallet;

import java.util.Map;
import java.util.Objects;

// Describes simple operations with complex controller arguments
@RestController
public class ComplexObjectController {

	// MAPS
	@RequestMapping(value = "/complex/parameter_map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addParameterMap(@RequestParam Map<String, String> parameters) {
		if (Objects.equals(parameters.get("x"), "1234"))
			throw new IllegalArgumentException("Parameter x must not be 1234");

		if (Objects.equals(parameters.get("y"), "12345"))
			throw new IllegalArgumentException("Parameter y must not be 12345");

		return parameters.get("x") + parameters.get("y");
	}

	@RequestMapping(value = "/complex/header_map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addHeadersMap(@RequestParam Map<String, String> headers) {
		if (Objects.equals(headers.get("x"), "1234"))
			throw new IllegalArgumentException("Parameter x must not be 1234");

		if (Objects.equals(headers.get("y"), "12345"))
			throw new IllegalArgumentException("Parameter y must not be 12345");

		return headers.get("x") + headers.get("y");
	}

	// TODO: Specify requests parts
	@RequestMapping(value = "/complex/matrix_map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addMatrixMap(@MatrixVariable Map<String, String> matrix) {
		if (Objects.equals(matrix.get("x"), "1234"))
			throw new IllegalArgumentException("Parameter x must not be 1234");

		if (Objects.equals(matrix.get("y"), "12345"))
			throw new IllegalArgumentException("Parameter y must not be 12345");

		return matrix.get("x") + matrix.get("y");
	}

	@RequestMapping(value = "/complex/path_map/{x}/{y}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addPathMap(@PathVariable Map<String, String> matrix) {
		if (Objects.equals(matrix.get("x"), "1234"))
			throw new IllegalArgumentException("Parameter x must not be 1234");

		if (Objects.equals(matrix.get("y"), "12345"))
			throw new IllegalArgumentException("Parameter y must not be 12345");

		return matrix.get("x") + matrix.get("y");
	}


	// Complex object via different attributes
	@RequestMapping(value = "/complex/wallet_param/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String walletMoneyFromParam(Wallet wallet) {
		if (Objects.equals(wallet.getCash(), 10))
			throw new IllegalArgumentException("Cash must not be 10");

		return wallet.getId().toString();
	}

	@RequestMapping(value = "/complex/wallet_header/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String walletMoneyFromHeader(@RequestHeader Wallet wallet) {
		if (Objects.equals(wallet.getCash(), 10))
			throw new IllegalArgumentException("Cash must not be 10");

		return wallet.getId().toString();
	}
}
