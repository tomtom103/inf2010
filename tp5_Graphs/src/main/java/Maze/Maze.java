package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {
    private static class Point {
        final Integer x, y;
        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class queueNode {
        Point pt;
        int dist;
        public queueNode(Point pt, Integer dist) {
            this.pt = pt;
            this.dist = dist;
        }
    }

    static boolean isValid(int row, int col, int maxRow, int maxCol) {
        return (row >= 0) && (row < maxRow) && (col >= 0) && (col < maxCol);
    }

    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    /** TODO
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        // Max values for maze size

        if(maze.size() == 0) {
            return null;
        }
        final int ROW = maze.size();
        final int COL = maze.get(0).size();
        // Find indexes of starting end ending points
        boolean[] entranceExit = { false, false };
        Point entrance = new Point(null, null), exit = new Point(null, null);
        OuterLoop:
        for(int row = 0; row < maze.size(); row++) {
            for(int col = 0; col < maze.get(row).size(); col++) {
                if(maze.get(row).get(col).equals(Tile.Exit)) {
                    if(entranceExit[0]) {
                        if(entranceExit[1]) {
                            break OuterLoop;
                        } else {
                            entranceExit[1] = true;
                            exit = new Point(row, col);
                        }
                    } else {
                        entranceExit[0] = true;
                        entrance = new Point(row, col);
                    }
                }
            }
        }

        printMaze(maze);

        if(!entranceExit[0] || !entranceExit[1]) {
            return null;
        }

        boolean[][] visited = new boolean[ROW][COL];

        visited[entrance.x][entrance.y] = true;

        Queue<queueNode> q = new LinkedList<>();

        queueNode s = new queueNode(entrance, 0);

        q.add(s);

        while(!q.isEmpty()) {
            queueNode current = q.peek();
            Point pt = current.pt;


            q.remove();

            for(int i = 0; i < 4; i++) {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                if(isValid(row, col, ROW, COL) && (maze.get(row).get(col).equals(Tile.Floor) || maze.get(row).get(col).equals(Tile.Exit)) && !visited[row][col]) {
                    visited[row][col] = true;
                    queueNode adjCell = new queueNode(new Point(row, col), current.dist + 1);
                    q.add(adjCell);
                }
            }
            if(pt.x.equals(exit.x) && pt.y.equals(exit.y)) {
                return current.dist;
            }
        }
        return null;
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

