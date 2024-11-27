package org.usvm.spring.benchmarks.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.usvm.spring.benchmarks.model.Wallet;


import java.util.Objects;

@RestController
public class SimpleController {
	// Passing arguments with different methods
	@RequestMapping(value = "/simple/increment_from_param", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incrementParam(@RequestParam(name = "sampleParameter") Integer target) {
		if (Objects.equals(target, 123))
			throw new IllegalArgumentException("Parameter target must not be 123");

		return String.format("%d", target + 1);
	}

	@RequestMapping(value = "/simple/increment_from_body", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incrementBody(@RequestBody String body) {
		if (Objects.equals(body, "123"))
			throw new IllegalArgumentException("Body must not be 123");

		return body + "1";
	}

	// Multipart https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/multipart-forms.html
	@RequestMapping(value = "/simple/increment_from_form",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String incrementMultipart(@RequestPart Wallet wallet) {
		if (Objects.equals(wallet.getCash(), 123))
			throw new IllegalArgumentException("Body must not be 123");

		return wallet.getCash() + "1";
	}

	// Name is wonky to test correct case processing
	@RequestMapping(value = "/simple/increment_from_header", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incrementHeader(@RequestHeader("Sample_HeAdEr") String header) {
		if (Objects.equals(header, "1234"))
			throw new IllegalArgumentException("Header must not be 1234");

		return header + "1";
	}

	// Name is wonky to test correct case processing
	@RequestMapping(value = "/simple/concat_stuff_from_request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incrementFromRequest(WebRequest request) {
		var parameterA = request.getParameter("a");
		var parameterB = request.getParameter("b");
		var headerC = request.getHeader("c");

		if (Objects.equals(headerC, "123") || Objects.equals(parameterB, "321"))
			throw new IllegalArgumentException("C and B must not contain 123 and 321");

		return parameterA + parameterB + headerC;
	}

	// Constraints on header within request mapping annotation
	@RequestMapping(value = "/simple/increment_from_header",
		method = {RequestMethod.GET, RequestMethod.POST},
		produces = MediaType.APPLICATION_JSON_VALUE,
		headers = {"Header_Access=Success", "Header_Access_2=Success_2"})
	public String checkHeadersConstraintsAndIncrementHeader(@RequestHeader("Sample_HeAdEr") String header) {
		if (Objects.equals(header, "1234"))
			throw new IllegalArgumentException("Header must not be 1234");

		return header + "1";
	}

	// Constraints on params within request mapping annotation
	@RequestMapping(value = "/simple/increment_from_header",
		method = {RequestMethod.GET, RequestMethod.DELETE},
		produces = MediaType.APPLICATION_JSON_VALUE,
		params = {"Param_Access=Success", "Param_Access_2=Success_2"})
	public String checkParamsConstraintsAndIncrementHeader(@RequestHeader("Sample_HeAdEr") String header) {
		if (Objects.equals(header, "1234"))
			throw new IllegalArgumentException("Header must not be 1234");

		return header + "1";
	}
}
