package org.usvm.spring.benchmarks.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

/*
 * Throws Illegal argument exception if 1000 was found in:
 * - Header SAMPLE_HEADER
 * - Parameter sampleParameter
 * - Body
 * - TODO: Form
 * - TODO: All headers, params, form values
 */

@Component
@Order(1)
public class StopThousandFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		var sampleHeader = req.getHeader("SAMPLE_HEADER");
		if (sampleHeader != null && sampleHeader.equals("1000"))
			throw new IllegalArgumentException("Header SAMPLE_HEADER must not be provided set to 1000");

		var sampleParameter = req.getParameter("sampleParameter");
		if (sampleParameter != null && sampleParameter.equals("1000"))
			throw new IllegalArgumentException("Parameter sampleParameter must not be set to 1000");

//		var bodyStream = req.getReader();
//		var body = bodyStream.lines().collect(Collectors.joining());
//		if (body.equals("1000"))
//			throw new IllegalArgumentException("Parameter sampleParameter must not be set to 1000");

		request.getParameterMap();
		chain.doFilter(request, response);
	}
}
