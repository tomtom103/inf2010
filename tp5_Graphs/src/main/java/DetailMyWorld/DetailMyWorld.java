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
                    if(isBreadthFirstSearch){
                        listeSet.add(breadthSearch(i,j,world,worldVisitedMap));
                    }
                    else{
                        listeSet.add(depthSearch(i,j,world,worldVisitedMap));
                    }

                }
            }
        }


        return listeSet;
    }

    private static Set<Integer> depthSearch(int x, int y, ArrayList<ArrayList<Integer>> world, ArrayList<ArrayList<Boolean>> worldVisitedMap){
        Set<Integer> set = new HashSet<>();
        Stack<Point> queue = new Stack<>();
        queue.add(new Point(x,y));
        while(!queue.isEmpty()){
            Point point = new Point(queue.pop());
            if(!set.contains(world.get(point.x).get(point.y))){
                set.add(world.get(point.x).get(point.y));
            }

            //améliorer en vérifiant si déjà visiter avant d'ajouter au queue
            if(!worldVisitedMap.get(point.x).get(point.y)){
                if(point.x > 0){
                    if(world.get(point.x-1).get(point.y) != 0 && !queue.contains(new Point(point.x-1, point.y))){
                        queue.add(new Point(point.x-1, point.y));
                    }
                }
                if(point.x < world.size()-1){
                    if(world.get(point.x+1).get(point.y) != 0 && !queue.contains(new Point(point.x+1, point.y))){
                        queue.add(new Point(point.x+1, point.y));
                    }
                }
                if(point.y > 0){
                    if(world.get(point.x).get(point.y-1) != 0 && !queue.contains(new Point(point.x, point.y-1))){
                        queue.add(new Point(point.x, point.y-1));
                    }
                }
                if(point.y < world.get(0).size()-1){
                    if(world.get(point.x).get(point.y+1) != 0 && !queue.contains(new Point(point.x, point.y+1))){
                        queue.add(new Point(point.x, point.y+1));
                    }
                }

                worldVisitedMap.get(point.x).set(point.y,true);

            }

        }
        return set;
    }
    private static Set<Integer> breadthSearch(int x, int y, ArrayList<ArrayList<Integer>> world, ArrayList<ArrayList<Boolean>> worldVisitedMap){
        Set<Integer> set = new HashSet<>();
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x,y));
        while(!queue.isEmpty()){
            Point point = new Point(queue.poll());
            if(!set.contains(world.get(point.x).get(point.y))){
                set.add(world.get(point.x).get(point.y));
            }

            //améliorer en vérifiant si déjà visiter avant d'ajouter au queue
            if(!worldVisitedMap.get(point.x).get(point.y)){
                if(point.x > 0){
                    if(world.get(point.x-1).get(point.y) != 0 && !queue.contains(new Point(point.x-1, point.y))){
                        queue.add(new Point(point.x-1, point.y));
                    }
                }
                if(point.x < world.size()-1){
                    if(world.get(point.x+1).get(point.y) != 0 && !queue.contains(new Point(point.x+1, point.y))){
                        queue.add(new Point(point.x+1, point.y));
                    }
                }
                if(point.y > 0){
                    if(world.get(point.x).get(point.y-1) != 0 && !queue.contains(new Point(point.x, point.y-1))){
                        queue.add(new Point(point.x, point.y-1));
                    }
                }
                if(point.y < world.get(0).size()-1){
                    if(world.get(point.x).get(point.y+1) != 0 && !queue.contains(new Point(point.x, point.y+1))){
                        queue.add(new Point(point.x, point.y+1));
                    }
                }

                worldVisitedMap.get(point.x).set(point.y,true);

            }

        }
        return set;
    }

    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
