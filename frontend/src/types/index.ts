export interface AlgorithmStep {
  stepNumber: number;
  description: string;
  array: number[];
  metadata: Record<string, any>;
  highlightedIndices: number[];
}

export interface GraphNode {
  id: number;
  label: string;
  x: number;
  y: number;
  edges: Edge[];
}

export interface Edge {
  to: number;
  weight: number;
}

export interface GraphStep {
  stepNumber: number;
  description: string;
  nodes: GraphNode[];
  visitedNodes: number[];
  currentNodes: number[];
  metadata: Record<string, any>;
}

export type AlgorithmType = 'sorting' | 'searching' | 'graph';
export type SortingAlgorithm = 'bubble' | 'merge' | 'quick' | 'heap';
export type SearchingAlgorithm = 'linear' | 'binary';
export type GraphAlgorithm = 'bfs' | 'dfs' | 'dijkstra';