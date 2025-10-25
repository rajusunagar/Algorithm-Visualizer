package com.algorithmvisualizer.service;

import com.algorithmvisualizer.model.AlgorithmStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingServiceTest {
    
    @InjectMocks
    private SortingService sortingService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testBubbleSort() {
        int[] input = {64, 34, 25, 12, 22, 11, 90};
        List<AlgorithmStep> steps = sortingService.bubbleSort(input);
        
        assertNotNull(steps);
        assertFalse(steps.isEmpty());
        
        // Check if array is sorted in the final step
        List<Integer> finalArray = steps.get(steps.size() - 1).getArray();
        for (int i = 1; i < finalArray.size(); i++) {
            assertTrue(finalArray.get(i - 1) <= finalArray.get(i));
        }
    }
    
    @Test
    void testMergeSort() {
        int[] input = {38, 27, 43, 3, 9, 82, 10};
        List<AlgorithmStep> steps = sortingService.mergeSort(input);
        
        assertNotNull(steps);
        assertFalse(steps.isEmpty());
        
        // Check if array is sorted in the final step
        List<Integer> finalArray = steps.get(steps.size() - 1).getArray();
        for (int i = 1; i < finalArray.size(); i++) {
            assertTrue(finalArray.get(i - 1) <= finalArray.get(i));
        }
    }
    
    @Test
    void testQuickSort() {
        int[] input = {10, 7, 8, 9, 1, 5};
        List<AlgorithmStep> steps = sortingService.quickSort(input);
        
        assertNotNull(steps);
        assertFalse(steps.isEmpty());
        
        // Check if array is sorted in the final step
        List<Integer> finalArray = steps.get(steps.size() - 1).getArray();
        for (int i = 1; i < finalArray.size(); i++) {
            assertTrue(finalArray.get(i - 1) <= finalArray.get(i));
        }
    }
    
    @Test
    void testHeapSort() {
        int[] input = {12, 11, 13, 5, 6, 7};
        List<AlgorithmStep> steps = sortingService.heapSort(input);
        
        assertNotNull(steps);
        assertFalse(steps.isEmpty());
        
        // Check if array is sorted in the final step
        List<Integer> finalArray = steps.get(steps.size() - 1).getArray();
        for (int i = 1; i < finalArray.size(); i++) {
            assertTrue(finalArray.get(i - 1) <= finalArray.get(i));
        }
    }
}