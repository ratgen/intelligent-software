package dk.group6.astar;

import dk.group6.common.data.World;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Node {
    private Node previous;
    private double distance;
    private double radians;
    private int x;
    private int y;
    private boolean goal;
    private int goalX;
    private int goalY;
    private int travel;
    private double total;

    public Node() {
    }
    
    public Node(int x, int y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    Node(boolean b, int x, int y) {
        this.goal = b;
        this.goalX = x;
        this.goalY = y;
    }

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTravel(int travel) {
        this.travel = travel;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Node getPrevious() {
        return previous;
    }

    public double getDistance() {
        return distance;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double direction ){
        this.radians = direction;
    }

    public int getGoalX() {
        return goalX;
    }

    public int getGoalY() {
        return goalY;
    }

    public int getTravel() {
        return this.travel;
    }

    public double getTotal() {
        return this.total;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double calcDistance(int x1, int y1, int x2, int y2) {
        double d = Math.sqrt(Math.abs(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

        return d;
    }

    /**
     * Returns the path to node n from its parent, and the parent of its parent.
     *
     * @param   n   Node from which to get the path
     * @return     A linkedList with the path, containing doubles
     */
    public LinkedList<Double> getPath(Node n) {
        LinkedList<Double> s = new LinkedList<>();

        s.addFirst(n.getRadians());

        while (n.getPrevious() != null) {
            n = n.getPrevious();
            s.addFirst(n.getRadians());
        }

        s.remove(0);
        return s;
    }

    /**
     * Expands a node.
     * Expanding a node gets the neighbours of the the node current, if they are not already present
     * in the set of explored nodes. 
     *
     * @param   current     is the Node to expand
     * @param   goal        is the goal Node 
     * @param   world       is the World, containing entities and data about the map
     * @param   explored    is a HashSet of explored nodes
     * @return              ArrayList of the expanded nodes
     */
    public ArrayList<Node> expand(Node current, Node goal, World world, HashSet<Node> explored) {
        ArrayList<Node> ways = new ArrayList<>();

        ArrayList<Node> neighbours = getNeighbours(current, world);

        for (Node node : neighbours) {
            if (!explored.contains(node)) {
                node.setDistance(calcDistance(node.getX(), node.getY(), goal.getGoalX(), goal.getGoalY()));
                node.setTravel(current.getTravel() + 1);
                node.setTotal(node.getTravel() + node.getDistance());
                node.setRadians(getDirection(current, node));
                node.setPrevious(current);
                ways.add(node);
                explored.add(node);
            }             
        }
        return ways;
    }

    /**
     * Get the radian value of the direction.
     * The radian value is gotten from the coordinate difference between the nodes.
     *
     * @param   current     the Node from which to calculate from 
     * @param   newNode     the Node which has moved in some direction
     * @return              a double, which is the direction
     *
     */
    public double getDirection(Node current, Node newNode){
        switch(newNode.getX() - current.getX()  ){
            case 10:
                return Math.PI/2;
            case -10:
                return -Math.PI/2;
            default:
                break;
        }

        switch(newNode.getY() - current.getY()){
            case 10:
                return 0;
            case -10:
                return Math.PI;
            default: 
                break;
        }
        throw new IllegalArgumentException("one of nodes not valid");
    }

    /**
     * Returns valid neighbours of the node.
     * 
     * @param   n           Node from which to calculate the neighbours
     * @param   world       Object containing data about the map
     * @return  validNodes  ArrayList containing the neighbour nodes
     */
    public ArrayList<Node> getNeighbours(Node n, World world) {
        ArrayList<Node> validNodes = new ArrayList<>();
        ArrayList<Node> pos = new ArrayList<>();       

        int offset = 10;
        int dist = 45;

        pos.add(new Node(n.getX() - offset, n.getY()));
        pos.add(new Node(n.getX(), n.getY() + offset));
        pos.add(new Node(n.getX() + offset, n.getY()));
        pos.add(new Node(n.getX(), n.getY() - offset));

        for (Node node : pos) {
            int x, y;
            x = node.getX();
            y = node.getY();
            if (world.isValidCell(x, y) //bottom left
                    && world.isValidCell(x + dist ,y ) //bottom right
                    && world.isValidCell(x + dist ,y + dist ) //top right
                    && world.isValidCell(x ,y + dist ) //top right
               )	
            {
                validNodes.add(node);
            }
        }

        return validNodes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).getX() == this.getX() && ((Node) obj).getY() == this.getY();
        }
        return false;
    }

    @Override
    public int hashCode(){
        return ((Integer) this.getY()).hashCode() * ((Integer) this.getX()).hashCode();
    }

}
