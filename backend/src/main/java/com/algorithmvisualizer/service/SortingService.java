package com.algorithmvisualizer.service;

import com.algorithmvisualizer.model.AlgorithmStep;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SortingService {
    
    public List<AlgorithmStep> bubbleSort(int[] array) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone();
        int n = arr.length;
        
        steps.add(new AlgorithmStep(0, "Initial array", Arrays.stream(arr).boxed().toList(), 
                Map.of("comparisons", 0, "swaps", 0), List.of()));
        
        int stepNum = 1, comparisons = 0, swaps = 0;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    
                    steps.add(new AlgorithmStep(stepNum++, 
                            String.format("Swapped %d and %d", temp, arr[j]), 
                            Arrays.stream(arr).boxed().toList(),
                            Map.of("comparisons", comparisons, "swaps", swaps),
                            List.of(j, j + 1)));
                }
            }
        }
        
        steps.add(new AlgorithmStep(stepNum, "Sorting complete", 
                Arrays.stream(arr).boxed().toList(),
                Map.of("comparisons", comparisons, "swaps", swaps), List.of()));
        
        return steps;
    }
    
    public List<AlgorithmStep> mergeSort(int[] array) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone();
        
        steps.add(new AlgorithmStep(0, "Initial array", Arrays.stream(arr).boxed().toList(), 
                Map.of(), List.of()));
        
        mergeSortHelper(arr, 0, arr.length - 1, steps);
        
        steps.add(new AlgorithmStep(steps.size(), "Sorting complete", 
                Arrays.stream(arr).boxed().toList(), Map.of(), List.of()));
        
        return steps;
    }
    
    private void mergeSortHelper(int[] arr, int left, int right, List<AlgorithmStep> steps) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortHelper(arr, left, mid, steps);
            mergeSortHelper(arr, mid + 1, right, steps);
            merge(arr, left, mid, right, steps);
        }
    }
    
    private void merge(int[] arr, int left, int mid, int right, List<AlgorithmStep> steps) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        System.arraycopy(temp, 0, arr, left, temp.length);
        
        List<Integer> highlighted = new ArrayList<>();
        for (int idx = left; idx <= right; idx++) {
            highlighted.add(idx);
        }
        
        steps.add(new AlgorithmStep(steps.size(), 
                String.format("Merged subarrays [%d-%d] and [%d-%d]", left, mid, mid + 1, right),
                Arrays.stream(arr).boxed().toList(),
                Map.of("left", left, "right", right), highlighted));
    }
    
    public List<AlgorithmStep> quickSort(int[] array) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone();
        
        steps.add(new AlgorithmStep(0, "Initial array", Arrays.stream(arr).boxed().toList(), 
                Map.of(), List.of()));
        
        quickSortHelper(arr, 0, arr.length - 1, steps);
        
        steps.add(new AlgorithmStep(steps.size(), "Sorting complete", 
                Arrays.stream(arr).boxed().toList(), Map.of(), List.of()));
        
        return steps;
    }
    
    private void quickSortHelper(int[] arr, int low, int high, List<AlgorithmStep> steps) {
        if (low < high) {
            int pi = partition(arr, low, high, steps);
            quickSortHelper(arr, low, pi - 1, steps);
            quickSortHelper(arr, pi + 1, high, steps);
        }
    }
    
    private int partition(int[] arr, int low, int high, List<AlgorithmStep> steps) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                steps.add(new AlgorithmStep(steps.size(),
                        String.format("Partitioning: moved %d to left of pivot %d", arr[i], pivot),
                        Arrays.stream(arr).boxed().toList(),
                        Map.of("pivot", pivot, "pivotIndex", high), List.of(i, j, high)));
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        steps.add(new AlgorithmStep(steps.size(),
                String.format("Placed pivot %d at correct position", pivot),
                Arrays.stream(arr).boxed().toList(),
                Map.of("pivot", pivot, "pivotIndex", i + 1), List.of(i + 1)));
        
        return i + 1;
    }
    
    public List<AlgorithmStep> heapSort(int[] array) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone();
        int n = arr.length;
        
        steps.add(new AlgorithmStep(0, "Initial array", Arrays.stream(arr).boxed().toList(), 
                Map.of(), List.of()));
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, steps);
        }
        
        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            steps.add(new AlgorithmStep(steps.size(),
                    String.format("Moved max element %d to position %d", temp, i),
                    Arrays.stream(arr).boxed().toList(),
                    Map.of("heapSize", i), List.of(0, i)));
            
            heapify(arr, i, 0, steps);
        }
        
        steps.add(new AlgorithmStep(steps.size(), "Sorting complete", 
                Arrays.stream(arr).boxed().toList(), Map.of(), List.of()));
        
        return steps;
    }
    
    private void heapify(int[] arr, int n, int i, List<AlgorithmStep> steps) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            
            steps.add(new AlgorithmStep(steps.size(),
                    String.format("Heapify: swapped %d and %d", temp, arr[i]),
                    Arrays.stream(arr).boxed().toList(),
                    Map.of("parent", i, "child", largest), List.of(i, largest)));
            
            heapify(arr, n, largest, steps);
        }
    }
}