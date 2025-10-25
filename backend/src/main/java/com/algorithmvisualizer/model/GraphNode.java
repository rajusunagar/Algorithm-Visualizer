package com.algorithmvisualizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphNode {
    private int id;
    private String label;
    private int x;
    private int y;
    private List<Edge> edges;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Edge {
        private int to;
        private int weight;
    }
}