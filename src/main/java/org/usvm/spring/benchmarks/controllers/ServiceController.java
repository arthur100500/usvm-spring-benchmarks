package org.usvm.spring.benchmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.benchmarks.service.CoolService;

@RestController
public class ServiceController {
	@Autowired
	CoolService coolService;

	@RequestMapping(value = "/service/gen_graph_node", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private String genGraphNode(){
		return String.valueOf(coolService.getGraphNode(10).value);
	}

	@RequestMapping(value = "/service/gen_int", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private String genInt(){
		return String.valueOf(coolService.getIntValue(10));
	}
}
