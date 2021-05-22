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
    //protected TreeSet<Node> explored = new TreeSet<>((node, node1) -> (Double.compare(node.getX() - node1.getX(), node.getY() - node1.getY())));
	protected HashSet<Node> explored = new HashSet();

    @Override
    public ArrayList<String> track(Entity from, Entity to, World world) throws NullPointerException {
	explored.clear();

		

		Node n1 = new Node(0, 0);
		Node n2 = new Node(0, 0);

		explored.add(n1);
		System.out.println(explored.contains(n2));
	
		if (to == null) {
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
            
            //current = path.remove(0);
			current = path.pollFirst();
            current.setDirections(positionFrom.getDirections());
			//System.out.println(current.getDistance());
            
            if (current.getDistance() < 10) {
                ArrayList<String> st = current.getPath(current);
				Collections.reverse(st);
                return st;
            }
            
            ArrayList<Node> ways = current.expand(current, goal, world, explored);

            path.addAll(ways);
            //Collections.sort(path, new CompareTotal());
           // System.out.println("state_space.size: "+path.size());
        }

		//throw new NullPointerException("A path to the to entity could not be found. Something is wrong.");
		return null;
    }
}
