# Algorithm Visualizer

A complete web application for visualizing algorithms with Spring Boot backend and React frontend.

## Features

### Sorting Algorithms
- Bubble Sort
- Merge Sort
- Quick Sort
- Heap Sort

### Searching Algorithms
- Linear Search
- Binary Search

### Graph Algorithms
- Breadth-First Search (BFS)
- Depth-First Search (DFS)
- Dijkstra's Shortest Path

## Architecture

- **Backend**: Spring Boot 3 (Java 17) with Maven
- **Frontend**: React 18 with TypeScript and Tailwind CSS
- **API Documentation**: Swagger UI
- **Containerization**: Docker and Docker Compose

## Quick Start

### Using Docker Compose (Recommended)

1. Clone the repository:
```bash
git clone <repository-url>
cd Algorithm-Visualizer
```

2. Run with Docker Compose:
```bash
docker-compose up --build
```

3. Access the application:
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

### Manual Setup

#### Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Run with Maven:
```bash
./mvnw spring-boot:run
```

Or build and run JAR:
```bash
./mvnw clean package
java -jar target/algorithm-visualizer-backend-1.0.0.jar
```

#### Frontend Setup

1. Navigate to frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start development server:
```bash
npm start
```

## API Examples

### Bubble Sort
```bash
curl -X POST http://localhost:8080/api/algorithms/sort/bubble \
  -H "Content-Type: application/json" \
  -d '{"array": [64, 34, 25, 12, 22, 11, 90]}'
```

**Response:**
```json
[
  {
    "stepNumber": 0,
    "description": "Initial array",
    "array": [64, 34, 25, 12, 22, 11, 90],
    "metadata": {"comparisons": 0, "swaps": 0},
    "highlightedIndices": []
  },
  {
    "stepNumber": 1,
    "description": "Swapped 64 and 34",
    "array": [34, 64, 25, 12, 22, 11, 90],
    "metadata": {"comparisons": 1, "swaps": 1},
    "highlightedIndices": [0, 1]
  }
]
```

### Binary Search
```bash
curl -X POST http://localhost:8080/api/algorithms/search/binary \
  -H "Content-Type: application/json" \
  -d '{"array": [11, 12, 22, 25, 34, 64, 90], "target": 22}'
```

### Graph BFS
```bash
curl -X POST http://localhost:8080/api/algorithms/graph/bfs \
  -H "Content-Type: application/json" \
  -d '{
    "nodes": [
      {"id": 0, "label": "A", "x": 100, "y": 100, "edges": [{"to": 1, "weight": 1}]},
      {"id": 1, "label": "B", "x": 200, "y": 100, "edges": []}
    ],
    "startNodeId": 0
  }'
```

## Frontend Animation Features

### Array Visualization
- **Bar Chart**: Elements displayed as bars with heights proportional to values
- **Highlighting**: Active elements highlighted in red with pulsing animation
- **Metadata Display**: Shows algorithm statistics (comparisons, swaps, etc.)
- **Step-by-step**: Navigate through each algorithm step

### Graph Visualization
- **Node Representation**: Circular nodes with labels
- **Edge Visualization**: Directed edges with arrow markers
- **State Indication**: 
  - Blue: Unvisited nodes
  - Green: Visited nodes
  - Red: Currently processing nodes (with pulsing animation)
- **Interactive Layout**: Nodes positioned with coordinates

### Controls
- **Play/Pause**: Automatic step progression
- **Step Forward/Backward**: Manual navigation
- **Reset**: Return to initial state
- **Speed Control**: Adjust animation speed (0.5x to 4x)
- **Progress Bar**: Visual indication of current step

## Testing

### Backend Tests
```bash
cd backend
./mvnw test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## Development

### Backend Structure
```
backend/
├── src/main/java/com/algorithmvisualizer/
│   ├── controller/     # REST controllers
│   ├── service/        # Business logic
│   ├── model/          # Data models
│   ├── config/         # Configuration
│   └── exception/      # Exception handling
└── src/test/java/      # Unit tests
```

### Frontend Structure
```
frontend/src/
├── components/         # React components
├── hooks/             # Custom hooks
├── services/          # API services
├── types/             # TypeScript types
└── utils/             # Utility functions
```

## Technologies Used

### Backend
- Spring Boot 3.2.0
- Java 17
- Maven
- Lombok
- SpringDoc OpenAPI (Swagger)
- JUnit 5 & Mockito

### Frontend
- React 18
- TypeScript
- Tailwind CSS
- Axios
- React Hooks

### DevOps
- Docker
- Docker Compose
