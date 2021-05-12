package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.entityparts.PositionPart;
import java.util.ArrayList;
import java.util.Collections;


public class AStar implements IPathFinderSPI{
    Node n = new Node();
    int c = 0;
    @Override
    public String[] track(Entity from, Entity to) {
        PositionPart pF = from.getPart(PositionPart.class);
        PositionPart pT = to.getPart(PositionPart.class);
        
        ArrayList<Node> path = new ArrayList<>();
        
        Node start = new Node(pF.getX(), pF.getY(), n.calcDistance(pF.getX(),pF.getY(),pT.getX(),pT.getY()));
        Node goal = new Node(true, 0, 0);
        
        path.add(start);
        
        while(!path.isEmpty()){
            Node current = path.remove(0);
            if (current.getDistance() == 0.0){
                String[] st = current.getPath(current);
                System.out.println(st[0]);
                return st;
            }
            ArrayList<Node> ways = n.expand(current);
            
            path.addAll(ways);
            Collections.sort(path, new CompareDistance());
            c++;
        }

        String[] temp = {"Right", "Up"};
        
        return temp;
    }

}
