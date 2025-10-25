import React from 'react';
import { GraphStep } from '../types';

interface GraphVisualizerProps {
  step: GraphStep;
}

const GraphVisualizer: React.FC<GraphVisualizerProps> = ({ step }) => {
  return (
    <div className="bg-white p-6 rounded-lg shadow-lg">
      <div className="mb-4">
        <h3 className="text-lg font-semibold text-gray-800">Step {step.stepNumber}</h3>
        <p className="text-gray-600">{step.description}</p>
      </div>
      
      <div className="relative bg-gray-50 rounded-lg" style={{ height: '400px', width: '100%' }}>
        <svg width="100%" height="100%" className="absolute inset-0">
          {/* Render edges */}
          {step.nodes.map(node => 
            node.edges?.map(edge => {
              const targetNode = step.nodes.find(n => n.id === edge.to);
              if (!targetNode) return null;
              
              return (
                <line
                  key={`${node.id}-${edge.to}`}
                  x1={node.x}
                  y1={node.y}
                  x2={targetNode.x}
                  y2={targetNode.y}
                  stroke="#6B7280"
                  strokeWidth="2"
                  markerEnd="url(#arrowhead)"
                />
              );
            })
          )}
          
          {/* Arrow marker definition */}
          <defs>
            <marker
              id="arrowhead"
              markerWidth="10"
              markerHeight="7"
              refX="9"
              refY="3.5"
              orient="auto"
            >
              <polygon
                points="0 0, 10 3.5, 0 7"
                fill="#6B7280"
              />
            </marker>
          </defs>
        </svg>
        
        {/* Render nodes */}
        {step.nodes.map(node => {
          const isVisited = step.visitedNodes.includes(node.id);
          const isCurrent = step.currentNodes.includes(node.id);
          
          return (
            <div
              key={node.id}
              className={`absolute transform -translate-x-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center text-white font-bold transition-all duration-300 ${
                isCurrent 
                  ? 'bg-red-500 animate-pulse-fast scale-125' 
                  : isVisited 
                    ? 'bg-green-500' 
                    : 'bg-blue-500'
              }`}
              style={{ left: node.x, top: node.y }}
            >
              {node.label || node.id}
            </div>
          );
        })}
      </div>
      
      {step.metadata && Object.keys(step.metadata).length > 0 && (
        <div className="mt-4 bg-gray-50 p-3 rounded">
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

export default GraphVisualizer;