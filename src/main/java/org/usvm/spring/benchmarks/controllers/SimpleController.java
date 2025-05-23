package org.usvm.spring.benchmarks.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.usvm.spring.benchmarks.model.GraphNode;
import org.usvm.spring.benchmarks.model.Wallet;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


import java.util.Objects;

@RestController
public class SimpleController {

	private void emptyMethod(String arg) {

	}

	// Passing arguments with different methods
	@RequestMapping(value = "/simple/increment_from_param", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int incrementParam(@RequestParam(name = "sampleParameter") Integer target) {
		if (Objects.equals(target, 123))
			throw new IllegalArgumentException("Parameter target must not be 123");

		emptyMethod("");
		return target + 1;
	}


	// Passing arguments with different methods
	@RequestMapping(value = "/simple/float/{f}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public float squareFloat(@PathVariable(name = "f") Integer f) {
		return 1;
	}

	@RequestMapping(value = "/simple/increment_from_body", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incrementBody(@RequestBody String body) {
		if (Objects.equals(body, "123"))
			throw new IllegalArgumentException("Body must not be 123");

		emptyMethod("");
		return body + "1";
	}

	// Multipart https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/multipart-forms.html
	@RequestMapping(value = "/simple/increment_from_form",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String incrementMultipart(@RequestBody Wallet wallet) {
		if (Objects.equals(wallet.getCash(), 123))
			throw new IllegalArgumentException("Body must not be 123");

		emptyMethod("");
		return wallet.getCash() + "1";
	}

	// Name is wonky to test correct case processing
	@RequestMapping(value = "/simple/increment_from_header", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incrementHeader(@RequestHeader("Sample_HeAdEr") String header) {
		if (Objects.equals(header, "1234"))
			throw new IllegalArgumentException("Header must not be 1234");

		emptyMethod("");
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

		String result = parameterA + "|" + parameterB + "|" + headerC;
		emptyMethod(result);
		return result;
	}

	@RequestMapping(value = "/simple/notRequired", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String notRequiredArguments(
			@RequestParam(required = false) int param,
			@PathVariable(required = false) String path,
			@RequestBody(required = false) GraphNode requestBody
	) {
		System.out.println(1);
		if (param == 0)
			return "1";

		System.out.println(2);
		if (path == null)
			return "2";

		System.out.println(3);
		if (requestBody == null)
			return "3";

		System.out.println(0);
		return "0";
	}
}
