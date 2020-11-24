package DetailMyWorld;

import java.util.*;
import java.util.stream.Collectors;
import java.awt.Point;

public class DetailMyWorld {
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
        ArrayList<ArrayList<Boolean>> worldVisitedMap = new ArrayList<>();
        List<Set<Integer>> listeSet = new ArrayList<>();
        for (int i = 0; i < world.size();i++){
            ArrayList<Boolean> liste = new ArrayList<>();
            for (int j = 0; j < world.get(0).size(); j++){
                liste.add(false);
            }
            worldVisitedMap.add(liste);
        }

        for (int i= 0; i < world.size(); i++){
            for (int j=0; j < world.get(0).size(); j++){
                if(!worldVisitedMap.get(i).get(j) && world.get(i).get(j) != 0){
                    listeSet.add(breadthOrDepthSearch(i,j,world,worldVisitedMap, isBreadthFirstSearch));
                }
            }
        }
        return listeSet;
    }

    private static Set<Integer> breadthOrDepthSearch(int x, int y, ArrayList<ArrayList<Integer>> world, ArrayList<ArrayList<Boolean>> worldVisitedMap, boolean isBreadthSearch){
        Set<Integer> set = new HashSet<>();
        Queue<Point> queue = new ArrayDeque<>();
        Stack<Point> stack = new Stack<>();

        if(isBreadthSearch){
            queue.add(new Point(x,y));
        }
        else{
            stack.add(new Point(x,y));
        }
        while(!queue.isEmpty() || !stack.isEmpty()){
            Point point = isBreadthSearch? new Point(Objects.requireNonNull(queue.poll())):  new Point(stack.pop());
            set.add(world.get(point.x).get(point.y));
            if(!isVisited(point, worldVisitedMap)){
                addIfValid(point, 0, point.x, -1,0,queue,stack,isBreadthSearch,world);
                addIfValid(point, point.x, world.size()-1, 1,0,queue,stack,isBreadthSearch,world);
                addIfValid(point,0,point.y,0,-1,queue,stack,isBreadthSearch,world);
                addIfValid(point, point.y, world.get(0).size()-1, 0,1,queue,stack,isBreadthSearch,world);
                worldVisitedMap.get(point.x).set(point.y,true);
            }
        }
        return set;
    }

    private static boolean isVisited(Point point, ArrayList<ArrayList<Boolean>> worldVisitedMap){
        return worldVisitedMap.get(point.x).get(point.y);
    }

    private static void addIfValid(Point point, int firstVar, int lastVar, int xOffSet, int yOffSet,
                                       Queue<Point> queue, Stack<Point> stack, boolean isBreadthSearch,
                                       ArrayList<ArrayList<Integer>> world){

        boolean collectionContains = isBreadthSearch? !queue.contains(new Point(point.x+xOffSet, point.y+yOffSet)):
                !stack.contains(new Point(point.x+xOffSet, point.y+yOffSet));

        if(firstVar < lastVar){
            if(world.get(point.x+xOffSet).get(point.y+yOffSet) != 0 && collectionContains){
                if(isBreadthSearch){
                    queue.add(new Point(point.x+xOffSet, point.y+yOffSet));
                }
                else{
                    stack.add(new Point(point.x+xOffSet, point.y+yOffSet));
                }
            }
        }
    }
    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
