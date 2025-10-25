import React, { useState } from 'react';
import { AlgorithmType, SortingAlgorithm, SearchingAlgorithm, GraphAlgorithm, GraphNode } from '../types';

interface AlgorithmSelectorProps {
  onExecute: (type: AlgorithmType, algorithm: string, data: any) => void;
  loading: boolean;
}

const AlgorithmSelector: React.FC<AlgorithmSelectorProps> = ({ onExecute, loading }) => {
  const [algorithmType, setAlgorithmType] = useState<AlgorithmType>('sorting');
  const [sortingAlgorithm, setSortingAlgorithm] = useState<SortingAlgorithm>('bubble');
  const [searchingAlgorithm, setSearchingAlgorithm] = useState<SearchingAlgorithm>('linear');
  const [graphAlgorithm, setGraphAlgorithm] = useState<GraphAlgorithm>('bfs');
  const [arrayInput, setArrayInput] = useState('64,34,25,12,22,11,90');
  const [searchTarget, setSearchTarget] = useState('22');

  const handleExecute = () => {
    if (algorithmType === 'sorting') {
      const array = arrayInput.split(',').map(n => parseInt(n.trim())).filter(n => !isNaN(n));
      onExecute('sorting', sortingAlgorithm, { array });
    } else if (algorithmType === 'searching') {
      const array = arrayInput.split(',').map(n => parseInt(n.trim())).filter(n => !isNaN(n));
      const target = parseInt(searchTarget);
      onExecute('searching', searchingAlgorithm, { array, target });
    } else if (algorithmType === 'graph') {
      // Sample graph data
      const nodes: GraphNode[] = [
        { id: 0, label: 'A', x: 100, y: 100, edges: [{ to: 1, weight: 4 }, { to: 2, weight: 2 }] },
        { id: 1, label: 'B', x: 300, y: 100, edges: [{ to: 2, weight: 1 }, { to: 3, weight: 5 }] },
        { id: 2, label: 'C', x: 200, y: 250, edges: [{ to: 3, weight: 8 }, { to: 4, weight: 10 }] },
        { id: 3, label: 'D', x: 400, y: 250, edges: [{ to: 4, weight: 2 }] },
        { id: 4, label: 'E', x: 300, y: 350, edges: [] },
      ];
      onExecute('graph', graphAlgorithm, { nodes, startNodeId: 0 });
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-lg">
      <h2 className="text-xl font-bold text-gray-800 mb-4">Algorithm Selector</h2>
      
      <div className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Algorithm Type
          </label>
          <select
            value={algorithmType}
            onChange={(e) => setAlgorithmType(e.target.value as AlgorithmType)}
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="sorting">Sorting</option>
            <option value="searching">Searching</option>
            <option value="graph">Graph</option>
          </select>
        </div>

        {algorithmType === 'sorting' && (
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Sorting Algorithm
            </label>
            <select
              value={sortingAlgorithm}
              onChange={(e) => setSortingAlgorithm(e.target.value as SortingAlgorithm)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="bubble">Bubble Sort</option>
              <option value="merge">Merge Sort</option>
              <option value="quick">Quick Sort</option>
              <option value="heap">Heap Sort</option>
            </select>
          </div>
        )}

        {algorithmType === 'searching' && (
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Searching Algorithm
            </label>
            <select
              value={searchingAlgorithm}
              onChange={(e) => setSearchingAlgorithm(e.target.value as SearchingAlgorithm)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="linear">Linear Search</option>
              <option value="binary">Binary Search</option>
            </select>
          </div>
        )}

        {algorithmType === 'graph' && (
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Graph Algorithm
            </label>
            <select
              value={graphAlgorithm}
              onChange={(e) => setGraphAlgorithm(e.target.value as GraphAlgorithm)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="bfs">Breadth-First Search</option>
              <option value="dfs">Depth-First Search</option>
              <option value="dijkstra">Dijkstra's Algorithm</option>
            </select>
          </div>
        )}

        {(algorithmType === 'sorting' || algorithmType === 'searching') && (
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Array (comma-separated)
            </label>
            <input
              type="text"
              value={arrayInput}
              onChange={(e) => setArrayInput(e.target.value)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="64,34,25,12,22,11,90"
            />
          </div>
        )}

        {algorithmType === 'searching' && (
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Search Target
            </label>
            <input
              type="number"
              value={searchTarget}
              onChange={(e) => setSearchTarget(e.target.value)}
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="22"
            />
          </div>
        )}

        <button
          onClick={handleExecute}
          disabled={loading}
          className="w-full px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
        >
          {loading ? 'Loading...' : 'Visualize Algorithm'}
        </button>
      </div>
    </div>
  );
};

export default AlgorithmSelector;