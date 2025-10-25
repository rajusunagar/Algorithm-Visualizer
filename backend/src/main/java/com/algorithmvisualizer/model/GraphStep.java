package com.algorithmvisualizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphStep {
    private int stepNumber;
    private String description;
    private List<GraphNode> nodes;
    private List<Integer> visitedNodes;
    private List<Integer> currentNodes;
    private Map<String, Object> metadata;
}