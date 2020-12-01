package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {
    private static final int NUM_ENTRANCE_EXIT = 2;
    private static final int NUM_DIRECTIONS = 4;
    private static final int[] xOffset = {-1, 0, 0, 1};
    private static final int[] yOffset = {0, -1, 1, 0};

    /** TODO
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        // Empty maze
        if(maze.size() == 0) return null;

        final int ROW_MAX = maze.size();
        final int COL_MAX = maze.get(0).size();

        boolean[] entranceExit = new boolean[NUM_ENTRANCE_EXIT];
        Point entrance = new Point();
        Point exit = new Point();

        outer : for(int row = 0; row < ROW_MAX; row++) {
            for(int col = 0; col < COL_MAX; col++) {
                if(maze.get(row).get(col).equals(Tile.Exit)) {
                    if(entranceExit[0]) {
                        // We break out of nested loop once we have found both entrance and exit.
                        if(entranceExit[1]) break outer;

                        entranceExit[1] = true;
                        exit = new Point(row, col);
                    } else {
                        entranceExit[0] = true;
                        entrance = new Point(row, col);
                    }
                }
            }
        }
        // If we haven't found entrance or exit, maze not solvable, return null
        if(!entranceExit[0] || !entranceExit[1]) {
            return null;
        }
        boolean[][] visited = new boolean[ROW_MAX][COL_MAX];

        // Visit the first Point
        visited[entrance.x][entrance.y] = true;
        Queue<DistancePoint> queue = new LinkedList<>();
        // The first Point is at a distance of 0 from itself.
        DistancePoint distancePoint = new DistancePoint(entrance, 0);
        queue.add(distancePoint);

        // Implementation of BFS to find shortest path
        // BFS considers all paths starting from source which makes sure that the first time
        // we visit the destination. it will be the shortest path
        while(!queue.isEmpty()) {
            DistancePoint current = queue.peek();
            Point pt = current.pt;
            queue.poll();

            for(int i = 0; i < NUM_DIRECTIONS; i++) {
                int row = pt.x + xOffset[i];
                int col = pt.y + yOffset[i];

                if(isValid(row, col, ROW_MAX, COL_MAX, maze, visited)) {
                    visited[row][col] = true;
                    DistancePoint adjCell = new DistancePoint(new Point(row, col), current.dist + 1);
                    queue.add(adjCell);
                }
            }
            // If the current Point is the exit, return distance
            if(pt.x.equals(exit.x) && pt.y.equals(exit.y)) {
                return current.dist;
            }
        }
        // Queue is Empty, meaning that we haven't found a path, return null
        return null;
    }

    /**
     * Data structure to store cell coordinates
     */
    private static class Point {
        final Integer x, y;
        public Point() {
            this.x = null;
            this.y = null;
        }
        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Data structure to keep track of the distance from the start of a point
     */
    private static class DistancePoint {
        Point pt;
        int dist;
        public DistancePoint(Point pt, Integer dist) {
            this.pt = pt;
            this.dist = dist;
        }
    }

    /**
     * We verify that the Node is within maze range, that it is either a FLOOR or EXIT, and that it hasn't been visited yet
     *
     * @param row row index
     * @param col column index
     * @param maxRow maximum possible row index in matrix range
     * @param maxCol maximum possible column index in matrix range
     * @param maze 2D matrix containing maze data
     * @param visited 2D array containing information on which Tiles we have already visited
     * @return true if we can visit the tile, false otherwise
     */
    private static boolean isValid(int row, int col, int maxRow, int maxCol, ArrayList<ArrayList<Tile>> maze, boolean[][] visited) {
        return ((row >= 0) && (row < maxRow) && (col >= 0) && (col < maxCol))
                && (maze.get(row).get(col).equals(Tile.Floor) || maze.get(row).get(col).equals(Tile.Exit))
                && !visited[row][col];
    }

    /**
     * Print the maze to the console
     * @param maze the maze
     */
    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

