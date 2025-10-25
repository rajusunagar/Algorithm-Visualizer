import { useState, useCallback, useRef } from 'react';
import { AlgorithmStep, GraphStep } from '../types';

export const useVisualization = () => {
  const [steps, setSteps] = useState<AlgorithmStep[] | GraphStep[]>([]);
  const [currentStep, setCurrentStep] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);
  const [speed, setSpeed] = useState(1000);
  const intervalRef = useRef<NodeJS.Timeout | null>(null);

  const loadSteps = useCallback((newSteps: AlgorithmStep[] | GraphStep[]) => {
    setSteps(newSteps);
    setCurrentStep(0);
    setIsPlaying(false);
  }, []);

  const play = useCallback(() => {
    if (currentStep >= steps.length - 1) return;
    
    setIsPlaying(true);
    intervalRef.current = setInterval(() => {
      setCurrentStep(prev => {
        if (prev >= steps.length - 1) {
          setIsPlaying(false);
          if (intervalRef.current) {
            clearInterval(intervalRef.current);
          }
          return prev;
        }
        return prev + 1;
      });
    }, speed);
  }, [currentStep, steps.length, speed]);

  const pause = useCallback(() => {
    setIsPlaying(false);
    if (intervalRef.current) {
      clearInterval(intervalRef.current);
      intervalRef.current = null;
    }
  }, []);

  const reset = useCallback(() => {
    pause();
    setCurrentStep(0);
  }, [pause]);

  const stepForward = useCallback(() => {
    if (currentStep < steps.length - 1) {
      setCurrentStep(prev => prev + 1);
    }
  }, [currentStep, steps.length]);

  const stepBackward = useCallback(() => {
    if (currentStep > 0) {
      setCurrentStep(prev => prev - 1);
    }
  }, [currentStep]);

  return {
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
  };
};