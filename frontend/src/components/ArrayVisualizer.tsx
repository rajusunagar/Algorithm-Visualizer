import React from 'react';
import { AlgorithmStep } from '../types';

interface ArrayVisualizerProps {
  step: AlgorithmStep;
}

const ArrayVisualizer: React.FC<ArrayVisualizerProps> = ({ step }) => {
  const maxValue = Math.max(...step.array);
  
  return (
    <div className="bg-white p-6 rounded-lg shadow-lg">
      <div className="mb-4">
        <h3 className="text-lg font-semibold text-gray-800">Step {step.stepNumber}</h3>
        <p className="text-gray-600">{step.description}</p>
      </div>
      
      <div className="flex items-end justify-center space-x-2 mb-4" style={{ height: '300px' }}>
        {step.array.map((value, index) => {
          const height = (value / maxValue) * 250;
          const isHighlighted = step.highlightedIndices.includes(index);
          
          return (
            <div
              key={index}
              className={`flex flex-col items-center transition-all duration-300 ${
                isHighlighted ? 'transform scale-110' : ''
              }`}
            >
              <div
                className={`w-12 rounded-t transition-all duration-300 ${
                  isHighlighted 
                    ? 'bg-red-500 animate-pulse-fast' 
                    : 'bg-blue-500 hover:bg-blue-600'
                }`}
                style={{ height: `${height}px` }}
              />
              <div className="mt-2 text-sm font-medium text-gray-700">
                {value}
              </div>
              <div className="text-xs text-gray-500">
                {index}
              </div>
            </div>
          );
        })}
      </div>
      
      {step.metadata && Object.keys(step.metadata).length > 0 && (
        <div className="bg-gray-50 p-3 rounded">
          <h4 className="text-sm font-medium text-gray-700 mb-2">Metadata:</h4>
          <div className="grid grid-cols-2 gap-2 text-sm">
            {Object.entries(step.metadata).map(([key, value]) => (
              <div key={key} className="flex justify-between">
                <span className="text-gray-600">{key}:</span>
                <span className="font-medium">{String(value)}</span>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default ArrayVisualizer;