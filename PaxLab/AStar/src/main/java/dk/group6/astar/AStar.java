package dk.group6.astar;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import java.util.Comparator;
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
    protected HashSet<Node> explored = new HashSet<>();

    /**
     * Returns a LinkedList of Doubles for the correct path to the goal entity.
     * 
     * @param   from    the entity from which to the to Entity
     * @param   to      the entity to track
     * @param   world   contains data about the entites and the map
     * @return          LinkedList containg the path to the to Entity  
     *
     */
    @Override
    public LinkedList<Double> track(Entity from, Entity to, World world) {
        explored.clear();
        PositionPart positionFrom = from.getPart(PositionPart.class);
        
        if (to == null){
            return null;
        }
        
        PositionPart positionTo = to.getPart(PositionPart.class);

        TreeSet<Node> fringe = new TreeSet<>(new CompareTotal());

        Node goal = new Node(true, positionTo.getX(), positionTo.getY());
        Node start = new Node(positionFrom.getX(), positionFrom.getY(), current.calcDistance(positionFrom.getX(), positionFrom.getY(), goal.getGoalX(), goal.getGoalY()));
        fringe.add(start);

        while (!fringe.isEmpty()) {
            current = fringe.pollFirst();
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

class CompareTotal implements Comparator<Node> {

    /**
     * Compares one node to another.
     * This is useful when ordering the nodes, from best to worst.
     *
     * @param   n1  node 1 to be compared
     * @param   n2  node 2 to be compared
     * @returns     0 if the nodes are equal, less than 0 if n1 less than n2 
     *                  and greater than 0 if n1 is greater than n2.
     *
     */
    @Override
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.getTotal(), n2.getTotal());
    }
}
