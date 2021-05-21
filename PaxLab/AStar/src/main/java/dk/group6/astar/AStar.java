package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 *
 * @author peter
 */
public class AStar implements IPathFinderSPI  {

    Node current = new Node();
    protected static ArrayList<Node> explored = new ArrayList<>();

    @Override
    public ArrayList<String> track(Entity from, Entity to, World world) throws NullPointerException {
	explored.clear();
	
		if (to == null) {
			// throw new NullPointerException("The entity to be found does not exist");
                        return null;
		}

        PositionPart positionFrom = from.getPart(PositionPart.class);
        PositionPart positionTo = to.getPart(PositionPart.class);

        //ArrayList<Node> path = new ArrayList<>();
		TreeSet<Node> path = new TreeSet<>(new CompareTotal());

        Node goal = new Node(true, positionTo.getX(), positionTo.getY());
        Node start = new Node(positionFrom.getX(), positionFrom.getY(), current.calcDistance(positionFrom.getX(), positionFrom.getY(), goal.getGoalX(), goal.getGoalY()));
        path.add(start);

        while (!path.isEmpty()) {
            
            current = path.first();

            current.setDirections(positionFrom.getDirections());
            
            if (current.getDistance() < 10) {
                ArrayList<String> st = current.getPath(current);
				Collections.reverse(st);
                return st;
            }
            
            ArrayList<Node> ways = current.expand(current, goal, world);

            path.addAll(ways);
           // System.out.println("state_space.size: "+path.size());
        }

		//throw new NullPointerException("A path to the to entity could not be found. Something is wrong.");
		return null;
    }
}
