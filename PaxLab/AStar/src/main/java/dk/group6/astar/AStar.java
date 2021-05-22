package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author peter
 */
public class AStar implements IPathFinderSPI  {

    Node current = new Node();
	protected HashSet<Node> explored = new HashSet();

    @Override
    public LinkedList<String> track(Entity from, Entity to, World world) throws NullPointerException {
		explored.clear();
        PositionPart positionFrom = from.getPart(PositionPart.class);
        PositionPart positionTo = to.getPart(PositionPart.class);

		if (to == null){
			return null;
		}

		TreeSet<Node> fringe = new TreeSet<>(new CompareTotal());

        Node goal = new Node(true, positionTo.getX(), positionTo.getY());
        Node start = new Node(positionFrom.getX(), positionFrom.getY(), current.calcDistance(positionFrom.getX(), positionFrom.getY(), goal.getGoalX(), goal.getGoalY()));
        fringe.add(start);

        while (!fringe.isEmpty()) {
	
			current = fringe.pollFirst();
            current.setDirections(positionFrom.getDirections());
            
            if (current.getDistance() < 10) {
                return current.getPath(current);
            }
            
            ArrayList<Node> ways = current.expand(current, goal, world, explored);
			
			fringe.addAll(ways);
        }

		System.out.println("returning null");
		return null;
    }
}
