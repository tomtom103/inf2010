package DetailMyWorld;

import java.util.*;
import java.util.stream.Collectors;

public class DetailMyWorld {
    // Number of directions
    private static final int NUM_DIRECTIONS = 4;
    // Value of a water region
    private static final int WATER_REGION = 0;

    // Arrays to apply offsets to visit each direction
    private static final int[] xOffset = {0, -1, 1, 0};
    private static final int[] yOffset = {-1, 0, 0, 1};

    /**
     * Data structure to hold coordinates
     */
    private static class Point {
        Integer x;
        Integer y;
        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
        public Point(Point point) {
            this.x = point.x;
            this.y = point.y;
        }
    }
    /** TODO
     * Returns a list of all continents with their respective countries within `world`
     * @param world 2D table of shape M x N representing the world
     *                  0 : Water Region
     *                  Positive non-null value : Country region for country of that specific value
     * @param isBreadthFirstSearch
     *              If true, algorithm used should be Breadth-First Search
     *              If false, algorithm used should be Depth-First Search
     * @return List of all continents with their respective countries
     *              Continents should be in order from left to right, top to bottom
     *              Each continent should have its countries in order
     */
    public static List<Set<Integer>> findContinents(ArrayList<ArrayList<Integer>> world, boolean isBreadthFirstSearch) {
        // Create visited map, it is initialized at false
        boolean[][] worldVisitedMap = new boolean[world.size()][world.get(0).size()];
        List<Set<Integer>> listeSet = new ArrayList<>();
        for(int i = 0; i < world.size(); i++)
            for(int j = 0; j < world.get(0).size(); j++)
                if(!worldVisitedMap[i][j] && isValid(i, j, world.size(), world.get(i).size(), world))
                    listeSet.add(breadthOrDepthSearch(i, j, world, worldVisitedMap, isBreadthFirstSearch));

        return listeSet;
    }

    /**
     * Verify that a Point is within matrix range and that it is not a water region
     * @param row the row index
     * @param col the column index
     * @param maxRow maximum possible row index in matrix range
     * @param maxCol maximum possible column index in matrix range
     * @param world matrix containing all the data for the "countries"
     * @return true if we can visit the country, false otherwise
     */
    private static boolean isValid(int row, int col, int maxRow, int maxCol, ArrayList<ArrayList<Integer>> world) {
        return ((row >= 0) && (row < maxRow) && (col >= 0) && (col < maxCol)) && (world.get(row).get(col) != WATER_REGION);
    }

    /**
     * Perform BFS or DFS depending on isBreathSearch
     * @param x the row index
     * @param y the column index
     * @param world world matrix containing all the country data
     * @param worldVisitedMap boolean 2D array keeping track of visited countries
     * @param isBreadthSearch boolean to indicate if we should use BFS or DFS
     * @return a Set of countries that are in the same continent
     */
    private static Set<Integer> breadthOrDepthSearch(int x, int y, ArrayList<ArrayList<Integer>> world, boolean[][] worldVisitedMap, boolean isBreadthSearch){
        Set<Integer> set = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();
        Stack<Point> stack = new Stack<>();

        if(isBreadthSearch){
            queue.add(new Point(x,y));
        }
        else{
            stack.add(new Point(x,y));
        }
        while(isBreadthSearch ? !queue.isEmpty() : !stack.isEmpty()) {
            Point point = new Point(isBreadthSearch ? queue.poll() : stack.pop());

            set.add(world.get(point.x).get(point.y));

            if(!worldVisitedMap[point.x][point.y]) {
                for(int i = 0; i < NUM_DIRECTIONS; i++) {
                    int row = point.x + xOffset[i];
                    int col = point.y + yOffset[i];
                    Point newPoint = new Point(row, col);
                    boolean collectionContains = isBreadthSearch ? queue.contains(newPoint) :
                            stack.contains(newPoint);
                    if(isValid(row, col, world.size(), world.get(0).size(), world) && !collectionContains) {
                        if(isBreadthSearch)
                            queue.add(newPoint);
                        else
                            stack.add(newPoint);
                    }

                }
                worldVisitedMap[point.x][point.y] = true;
            }
        }
    }

    /**
     * Print the world to the console
     * @param world the world to print
     */
    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
