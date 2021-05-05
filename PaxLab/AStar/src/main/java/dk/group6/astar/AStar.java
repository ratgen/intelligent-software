package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class AStar implements IPathFinderSPI {
    Node n;
    @Override
    public String[] track(Entity from, Entity to, World world) {
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
            path.sort(Comparator.comparing(Node::getTotal));
        }
        
        String[] failed = {"fail"};
        return failed;
    }

}
