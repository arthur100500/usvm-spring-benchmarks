package org.usvm.spring.benchmarks.repository;

import org.springframework.stereotype.Repository;
import org.usvm.spring.benchmarks.model.GraphNode;

@Repository
public class GraphRepository {
    public GraphNode getNodeById(int id) {
        return new GraphNode(13, null, null);
    }
}
