import React, { useState } from 'react';
import AlgorithmSelector from './components/AlgorithmSelector';
import ArrayVisualizer from './components/ArrayVisualizer';
import GraphVisualizer from './components/GraphVisualizer';
import Controls from './components/Controls';
import { useVisualization } from './hooks/useVisualization';
import { algorithmApi } from './services/api';
import { AlgorithmType, AlgorithmStep, GraphStep } from './types';

function App() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [currentAlgorithmType, setCurrentAlgorithmType] = useState<AlgorithmType | null>(null);
  
  const {
    steps,
    currentStep,
    isPlaying,
    speed,
    loadSteps,
    play,
    pause,
    reset,
    stepForward,
    stepBackward,
    setSpeed,
  } = useVisualization();

  const handleExecuteAlgorithm = async (type: AlgorithmType, algorithm: string, data: any) => {
    setLoading(true);
    setError(null);
    setCurrentAlgorithmType(type);

    try {
      let result;
      
      if (type === 'sorting') {
        switch (algorithm) {
          case 'bubble':
            result = await algorithmApi.bubbleSort(data.array);
            break;
          case 'merge':
            result = await algorithmApi.mergeSort(data.array);
            break;
          case 'quick':
            result = await algorithmApi.quickSort(data.array);
            break;
          case 'heap':
            result = await algorithmApi.heapSort(data.array);
            break;
          default:
            throw new Error('Unknown sorting algorithm');
        }
      } else if (type === 'searching') {
        switch (algorithm) {
          case 'linear':
            result = await algorithmApi.linearSearch(data.array, data.target);
            break;
          case 'binary':
            result = await algorithmApi.binarySearch(data.array, data.target);
            break;
          default:
            throw new Error('Unknown searching algorithm');
        }
      } else if (type === 'graph') {
        switch (algorithm) {
          case 'bfs':
            result = await algorithmApi.bfs(data.nodes, data.startNodeId);
            break;
          case 'dfs':
            result = await algorithmApi.dfs(data.nodes, data.startNodeId);
            break;
          case 'dijkstra':
            result = await algorithmApi.dijkstra(data.nodes, data.startNodeId);
            break;
          default:
            throw new Error('Unknown graph algorithm');
        }
      }

      if (result) {
        loadSteps(result);
      }
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setLoading(false);
    }
  };

  const currentStepData = steps[currentStep];

  return (
    <div className="min-h-screen bg-gray-100">
      <div className="container mx-auto px-4 py-8">
        <header className="text-center mb-8">
          <h1 className="text-4xl font-bold text-gray-800 mb-2">Algorithm Visualizer</h1>
          <p className="text-gray-600">Interactive visualization of sorting, searching, and graph algorithms</p>
        </header>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          <div className="lg:col-span-1">
            <AlgorithmSelector onExecute={handleExecuteAlgorithm} loading={loading} />
          </div>

          <div className="lg:col-span-2 space-y-6">
            {error && (
              <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
                <strong>Error:</strong> {error}
              </div>
            )}

            {steps.length > 0 && (
              <>
                <Controls
                  isPlaying={isPlaying}
                  currentStep={currentStep}
                  totalSteps={steps.length}
                  speed={speed}
                  onPlay={play}
                  onPause={pause}
                  onReset={reset}
                  onStepForward={stepForward}
                  onStepBackward={stepBackward}
                  onSpeedChange={setSpeed}
                />

                {currentStepData && (
                  <>
                    {currentAlgorithmType === 'graph' ? (
                      <GraphVisualizer step={currentStepData as GraphStep} />
                    ) : (
                      <ArrayVisualizer step={currentStepData as AlgorithmStep} />
                    )}
                  </>
                )}
              </>
            )}

            {steps.length === 0 && !loading && (
              <div className="bg-white p-8 rounded-lg shadow-lg text-center">
                <h3 className="text-lg font-medium text-gray-800 mb-2">
                  Welcome to Algorithm Visualizer
                </h3>
                <p className="text-gray-600">
                  Select an algorithm from the left panel to start visualizing!
                </p>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;