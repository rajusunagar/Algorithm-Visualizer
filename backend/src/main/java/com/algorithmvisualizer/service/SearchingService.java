package com.algorithmvisualizer.service;

import com.algorithmvisualizer.model.AlgorithmStep;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchingService {
    
    public List<AlgorithmStep> linearSearch(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        
        steps.add(new AlgorithmStep(0, String.format("Searching for %d in array", target), 
                Arrays.stream(array).boxed().toList(), 
                Map.of("target", target, "found", false), List.of()));
        
        for (int i = 0; i < array.length; i++) {
            steps.add(new AlgorithmStep(i + 1, 
                    String.format("Checking index %d: %d", i, array[i]),
                    Arrays.stream(array).boxed().toList(),
                    Map.of("target", target, "currentIndex", i, "found", array[i] == target),
                    List.of(i)));
            
            if (array[i] == target) {
                steps.add(new AlgorithmStep(i + 2, 
                        String.format("Found %d at index %d", target, i),
                        Arrays.stream(array).boxed().toList(),
                        Map.of("target", target, "foundIndex", i, "found", true),
                        List.of(i)));
                return steps;
            }
        }
        
        steps.add(new AlgorithmStep(array.length + 1, 
                String.format("Target %d not found", target),
                Arrays.stream(array).boxed().toList(),
                Map.of("target", target, "found", false), List.of()));
        
        return steps;
    }
    
    public List<AlgorithmStep> binarySearch(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        
        steps.add(new AlgorithmStep(0, String.format("Searching for %d in sorted array", target), 
                Arrays.stream(sortedArray).boxed().toList(), 
                Map.of("target", target, "found", false), List.of()));
        
        int left = 0, right = sortedArray.length - 1;
        int stepNum = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            steps.add(new AlgorithmStep(stepNum++, 
                    String.format("Checking middle element at index %d: %d", mid, sortedArray[mid]),
                    Arrays.stream(sortedArray).boxed().toList(),
                    Map.of("target", target, "left", left, "right", right, "mid", mid),
                    List.of(left, mid, right)));
            
            if (sortedArray[mid] == target) {
                steps.add(new AlgorithmStep(stepNum, 
                        String.format("Found %d at index %d", target, mid),
                        Arrays.stream(sortedArray).boxed().toList(),
                        Map.of("target", target, "foundIndex", mid, "found", true),
                        List.of(mid)));
                return steps;
            }
            
            if (sortedArray[mid] < target) {
                left = mid + 1;
                steps.add(new AlgorithmStep(stepNum++, 
                        String.format("Target is greater, searching right half"),
                        Arrays.stream(sortedArray).boxed().toList(),
                        Map.of("target", target, "left", left, "right", right),
                        List.of()));
            } else {
                right = mid - 1;
                steps.add(new AlgorithmStep(stepNum++, 
                        String.format("Target is smaller, searching left half"),
                        Arrays.stream(sortedArray).boxed().toList(),
                        Map.of("target", target, "left", left, "right", right),
                        List.of()));
            }
        }
        
        steps.add(new AlgorithmStep(stepNum, 
                String.format("Target %d not found", target),
                Arrays.stream(sortedArray).boxed().toList(),
                Map.of("target", target, "found", false), List.of()));
        
        return steps;
    }
}