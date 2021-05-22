package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 * @author peter
 */
public class AStar implements IPathFinderSPI  {

    Node current = new Node();
	protected HashSet<Node> explored = new HashSet();

    @Override
    public ArrayList<String> track(Entity from, Entity to, World world) throws NullPointerException {
		explored.clear();
        PositionPart positionFrom = from.getPart(PositionPart.class);
        PositionPart positionTo = to.getPart(PositionPart.class);

        ArrayList<Node> fringe = new ArrayList<>();
		//TreeSet<Node> fringe = new TreeSet<>(new CompareTotal());

        Node goal = new Node(true, positionTo.getX(), positionTo.getY());
        Node start = new Node(positionFrom.getX(), positionFrom.getY(), current.calcDistance(positionFrom.getX(), positionFrom.getY(), goal.getGoalX(), goal.getGoalY()));
        fringe.add(start);

        while (!fringe.isEmpty()) {
	
			current = fringe.remove(0);
            current.setDirections(positionFrom.getDirections());
            
            if (current.getDistance() < 10) {

                ArrayList<String> st = current.getPath(current);
                return st;
            }
            
            ArrayList<Node> ways = current.expand(current, goal, world, explored);

			for (Node n : ways){
				if (!fringe.add(n)){
					System.out.println("node n exists in path exists in path: " + n.getX() + " y: " + n.getY() );
				}
			}
           	//System.out.println("state_space.size: " + fringe.size());
			Collections.sort(fringe, new CompareTotal());
        }

		//throw new NullPointerException("A fringe to the to entity could not be found. Something is wrong.");
		System.out.println("returning null");
		return null;
    }
}
