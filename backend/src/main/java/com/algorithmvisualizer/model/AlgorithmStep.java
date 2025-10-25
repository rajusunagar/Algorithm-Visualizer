package com.algorithmvisualizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmStep {
    private int stepNumber;
    private String description;
    private List<Integer> array;
    private Map<String, Object> metadata;
    private List<Integer> highlightedIndices;
}