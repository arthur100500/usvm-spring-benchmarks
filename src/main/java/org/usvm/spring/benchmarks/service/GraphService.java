package org.usvm.spring.benchmarks.service;

import org.springframework.stereotype.Service;
import org.usvm.spring.benchmarks.model.GraphNode;

@Service
public class GraphService {
    public GraphNode linkNode(GraphNode node) {
        node.right = new GraphNode(node.value - 1, null, null);
        node.left = new GraphNode(node.value + 1, null, null);
        return node;
    }
}
