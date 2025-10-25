package com.algorithmvisualizer.controller;

import com.algorithmvisualizer.model.AlgorithmStep;
import com.algorithmvisualizer.model.GraphNode;
import com.algorithmvisualizer.model.GraphStep;
import com.algorithmvisualizer.service.GraphService;
import com.algorithmvisualizer.service.SearchingService;
import com.algorithmvisualizer.service.SortingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/algorithms")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Algorithm Visualizer", description = "APIs for algorithm visualization")
public class AlgorithmController {
    
    private final SortingService sortingService;
    private final SearchingService searchingService;
    private final GraphService graphService;
    
    @PostMapping("/sort/bubble")
    @Operation(summary = "Bubble Sort", description = "Visualize bubble sort algorithm")
    public ResponseEntity<List<AlgorithmStep>> bubbleSort(@Valid @RequestBody ArrayRequest request) {
        return ResponseEntity.ok(sortingService.bubbleSort(request.array));
    }
    
    @PostMapping("/sort/merge")
    @Operation(summary = "Merge Sort", description = "Visualize merge sort algorithm")
    public ResponseEntity<List<AlgorithmStep>> mergeSort(@Valid @RequestBody ArrayRequest request) {
        return ResponseEntity.ok(sortingService.mergeSort(request.array));
    }
    
    @PostMapping("/sort/quick")
    @Operation(summary = "Quick Sort", description = "Visualize quick sort algorithm")
    public ResponseEntity<List<AlgorithmStep>> quickSort(@Valid @RequestBody ArrayRequest request) {
        return ResponseEntity.ok(sortingService.quickSort(request.array));
    }
    
    @PostMapping("/sort/heap")
    @Operation(summary = "Heap Sort", description = "Visualize heap sort algorithm")
    public ResponseEntity<List<AlgorithmStep>> heapSort(@Valid @RequestBody ArrayRequest request) {
        return ResponseEntity.ok(sortingService.heapSort(request.array));
    }
    
    @PostMapping("/search/linear")
    @Operation(summary = "Linear Search", description = "Visualize linear search algorithm")
    public ResponseEntity<List<AlgorithmStep>> linearSearch(@Valid @RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchingService.linearSearch(request.array, request.target));
    }
    
    @PostMapping("/search/binary")
    @Operation(summary = "Binary Search", description = "Visualize binary search algorithm")
    public ResponseEntity<List<AlgorithmStep>> binarySearch(@Valid @RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchingService.binarySearch(request.array, request.target));
    }
    
    @PostMapping("/graph/bfs")
    @Operation(summary = "Breadth-First Search", description = "Visualize BFS algorithm")
    public ResponseEntity<List<GraphStep>> bfs(@Valid @RequestBody GraphRequest request) {
        return ResponseEntity.ok(graphService.bfs(request.nodes, request.startNodeId));
    }
    
    @PostMapping("/graph/dfs")
    @Operation(summary = "Depth-First Search", description = "Visualize DFS algorithm")
    public ResponseEntity<List<GraphStep>> dfs(@Valid @RequestBody GraphRequest request) {
        return ResponseEntity.ok(graphService.dfs(request.nodes, request.startNodeId));
    }
    
    @PostMapping("/graph/dijkstra")
    @Operation(summary = "Dijkstra's Algorithm", description = "Visualize Dijkstra's shortest path algorithm")
    public ResponseEntity<List<GraphStep>> dijkstra(@Valid @RequestBody GraphRequest request) {
        return ResponseEntity.ok(graphService.dijkstra(request.nodes, request.startNodeId));
    }
    
    public static class ArrayRequest {
        @NotNull
        @NotEmpty
        public int[] array;
    }
    
    public static class SearchRequest {
        @NotNull
        @NotEmpty
        public int[] array;
        @NotNull
        public int target;
    }
    
    public static class GraphRequest {
        @NotNull
        @NotEmpty
        public List<GraphNode> nodes;
        @NotNull
        public int startNodeId;
    }
}