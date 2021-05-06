package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;


public class AStar implements IPathFinderSPI{
    Node n;
    
    @Override
    public String[] track(Entity from, Entity to) {
        System.out.println("123123123ndeodoe");
        ArrayList<Node> path = new ArrayList<>();
        
        
        Node start = new Node(from.getGridLocation(), n.calcDistance(from.getGridLocation(), to.getGridLocation()));
        Map<String, Integer> goal = to.getGridLocation();
        
        path.add(start);
        
        while(!path.isEmpty()){
            Node current = path.remove(0);
            if (current.coordinates.equals(goal)){
                return current.getPath(current);
            }
            Node[] ways = n.expand(current, goal);
            
            path.addAll(Arrays.asList(ways));
            Collections.sort(path);
            System.out.println(path);
        }
        
        String[] failed = {"fail"};
        return failed;
    }

}
