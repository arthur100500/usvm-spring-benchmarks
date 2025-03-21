package org.usvm.spring.benchmarks.model;


public class GraphNode {
    public int value;
    public GraphNode left;
    public GraphNode right;
    
    public GraphNode(int value, GraphNode right, GraphNode left) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
