package org.usvm.spring.benchmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.usvm.spring.benchmarks.model.GraphNode;
import org.usvm.spring.benchmarks.repository.GraphRepository;
import org.usvm.spring.benchmarks.service.GraphService;

@RestController
public class ServiceController {

    @Autowired
    GraphRepository repository;
    
    @Autowired
    GraphService service;
    
    @RequestMapping(value = "/service/link_node", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<GraphNode> linkNode(@RequestParam int node_id) {
        GraphNode node = repository.getNodeById(node_id);
        return new HttpEntity<>(service.linkNode(node));
    }
}
