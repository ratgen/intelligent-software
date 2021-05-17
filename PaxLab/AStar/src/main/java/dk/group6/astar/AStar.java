package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.entityparts.PositionPart;
import java.util.ArrayList;
import java.util.Collections;

public class AStar implements IPathFinderSPI {

    Node current = new Node();

    @Override
    public ArrayList<String> track(Entity from, Entity to) {
        PositionPart pF = from.getPart(PositionPart.class);
        PositionPart pT = to.getPart(PositionPart.class);

        ArrayList<Node> path = new ArrayList<>();

        Node goal = new Node(true, pT.getX(), pT.getY());
        Node start = new Node(pF.getX(), pF.getY(), current.calcDistance(pF.getX(), pF.getY(), goal.getGoalX(), goal.getGoalY()));

        path.add(start);

        while (!path.isEmpty()) {
            int c = 0;
            current = path.remove(0);
            
            current.setDirections(pF.getDirections());
            
            if (current.getDistance() < 10 || c > 100) {
                ArrayList<String> st = current.getPath(current);
                return st;
            }
            ArrayList<Node> ways = current.expand(current, goal);

            path.addAll(ways);
            Collections.sort(path, new CompareDistance());
            c++;
        }

        ArrayList<String> temp = new ArrayList<>();

        temp.add("Down");
        System.out.println("failed");

        return temp;
    }

}
