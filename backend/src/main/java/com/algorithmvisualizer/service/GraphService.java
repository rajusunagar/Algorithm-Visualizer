package com.algorithmvisualizer.service;

import com.algorithmvisualizer.model.GraphNode;
import com.algorithmvisualizer.model.GraphStep;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphService {
    
    public List<GraphStep> bfs(List<GraphNode> nodes, int startNodeId) {
        List<GraphStep> steps = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        steps.add(new GraphStep(0, "Starting BFS traversal", nodes, 
                new ArrayList<>(), List.of(startNodeId), Map.of("queue", List.of(startNodeId))));
        
        queue.offer(startNodeId);
        visited.add(startNodeId);
        int stepNum = 1;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            steps.add(new GraphStep(stepNum++, 
                    String.format("Visiting node %d", current), nodes,
                    new ArrayList<>(visited), List.of(current), 
                    Map.of("queue", new ArrayList<>(queue))));
            
            GraphNode currentNode = nodes.stream()
                    .filter(n -> n.getId() == current)
                    .findFirst().orElse(null);
            
            if (currentNode != null && currentNode.getEdges() != null) {
                for (GraphNode.Edge edge : currentNode.getEdges()) {
                    if (!visited.contains(edge.getTo())) {
                        visited.add(edge.getTo());
                        queue.offer(edge.getTo());
                        
                        steps.add(new GraphStep(stepNum++, 
                                String.format("Added node %d to queue", edge.getTo()), nodes,
                                new ArrayList<>(visited), List.of(edge.getTo()), 
                                Map.of("queue", new ArrayList<>(queue))));
                    }
                }
            }
        }
        
        steps.add(new GraphStep(stepNum, "BFS traversal complete", nodes,
                new ArrayList<>(visited), List.of(), Map.of("queue", List.of())));
        
        return steps;
    }
    
    public List<GraphStep> dfs(List<GraphNode> nodes, int startNodeId) {
        List<GraphStep> steps = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        steps.add(new GraphStep(0, "Starting DFS traversal", nodes, 
                new ArrayList<>(), List.of(startNodeId), Map.of("stack", List.of(startNodeId))));
        
        stack.push(startNodeId);
        
        int stepNum = 1;
        
        while (!stack.isEmpty()) {
            int current = stack.pop();
            
            if (!visited.contains(current)) {
                visited.add(current);
                
                steps.add(new GraphStep(stepNum++, 
                        String.format("Visiting node %d", current), nodes,
                        new ArrayList<>(visited), List.of(current), 
                        Map.of("stack", new ArrayList<>(stack))));
                
                GraphNode currentNode = nodes.stream()
                        .filter(n -> n.getId() == current)
                        .findFirst().orElse(null);
                
                if (currentNode != null && currentNode.getEdges() != null) {
                    for (GraphNode.Edge edge : currentNode.getEdges()) {
                        if (!visited.contains(edge.getTo())) {
                            stack.push(edge.getTo());
                            
                            steps.add(new GraphStep(stepNum++, 
                                    String.format("Added node %d to stack", edge.getTo()), nodes,
                                    new ArrayList<>(visited), List.of(edge.getTo()), 
                                    Map.of("stack", new ArrayList<>(stack))));
                        }
                    }
                }
            }
        }
        
        steps.add(new GraphStep(stepNum, "DFS traversal complete", nodes,
                new ArrayList<>(visited), List.of(), Map.of("stack", List.of())));
        
        return steps;
    }
    
    public List<GraphStep> dijkstra(List<GraphNode> nodes, int startNodeId) {
        List<GraphStep> steps = new ArrayList<>();
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        
        // Initialize distances
        for (GraphNode node : nodes) {
            distances.put(node.getId(), node.getId() == startNodeId ? 0 : Integer.MAX_VALUE);
        }
        
        pq.offer(new int[]{startNodeId, 0});
        
        steps.add(new GraphStep(0, "Starting Dijkstra's algorithm", nodes, 
                new ArrayList<>(), List.of(startNodeId), 
                Map.of("distances", new HashMap<>(distances))));
        
        int stepNum = 1;
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int nodeId = current[0];
            int dist = current[1];
            
            if (visited.contains(nodeId)) continue;
            
            visited.add(nodeId);
            
            steps.add(new GraphStep(stepNum++, 
                    String.format("Processing node %d with distance %d", nodeId, dist), nodes,
                    new ArrayList<>(visited), List.of(nodeId), 
                    Map.of("distances", new HashMap<>(distances))));
            
            GraphNode currentNode = nodes.stream()
                    .filter(n -> n.getId() == nodeId)
                    .findFirst().orElse(null);
            
            if (currentNode != null && currentNode.getEdges() != null) {
                for (GraphNode.Edge edge : currentNode.getEdges()) {
                    int newDist = dist + edge.getWeight();
                    if (newDist < distances.get(edge.getTo())) {
                        distances.put(edge.getTo(), newDist);
                        pq.offer(new int[]{edge.getTo(), newDist});
                        
                        steps.add(new GraphStep(stepNum++, 
                                String.format("Updated distance to node %d: %d", edge.getTo(), newDist), nodes,
                                new ArrayList<>(visited), List.of(edge.getTo()), 
                                Map.of("distances", new HashMap<>(distances))));
                    }
                }
            }
        }
        
        steps.add(new GraphStep(stepNum, "Dijkstra's algorithm complete", nodes,
                new ArrayList<>(visited), List.of(), 
                Map.of("distances", new HashMap<>(distances))));
        
        return steps;
    }
}