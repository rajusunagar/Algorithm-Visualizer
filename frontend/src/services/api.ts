import axios from 'axios';
import { AlgorithmStep, GraphStep, GraphNode } from '../types';

const API_BASE_URL = 'http://localhost:8080/api/algorithms';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const algorithmApi = {
  // Sorting algorithms
  bubbleSort: (array: number[]): Promise<AlgorithmStep[]> =>
    api.post('/sort/bubble', { array }).then(res => res.data),
  
  mergeSort: (array: number[]): Promise<AlgorithmStep[]> =>
    api.post('/sort/merge', { array }).then(res => res.data),
  
  quickSort: (array: number[]): Promise<AlgorithmStep[]> =>
    api.post('/sort/quick', { array }).then(res => res.data),
  
  heapSort: (array: number[]): Promise<AlgorithmStep[]> =>
    api.post('/sort/heap', { array }).then(res => res.data),

  // Searching algorithms
  linearSearch: (array: number[], target: number): Promise<AlgorithmStep[]> =>
    api.post('/search/linear', { array, target }).then(res => res.data),
  
  binarySearch: (array: number[], target: number): Promise<AlgorithmStep[]> =>
    api.post('/search/binary', { array, target }).then(res => res.data),

  // Graph algorithms
  bfs: (nodes: GraphNode[], startNodeId: number): Promise<GraphStep[]> =>
    api.post('/graph/bfs', { nodes, startNodeId }).then(res => res.data),
  
  dfs: (nodes: GraphNode[], startNodeId: number): Promise<GraphStep[]> =>
    api.post('/graph/dfs', { nodes, startNodeId }).then(res => res.data),
  
  dijkstra: (nodes: GraphNode[], startNodeId: number): Promise<GraphStep[]> =>
    api.post('/graph/dijkstra', { nodes, startNodeId }).then(res => res.data),
};