package dk.group6.astar;

import java.util.ArrayList;
import java.util.Comparator;

public class Node {

    Node previous;
    double distance;
    String direction;
    float x;
    float y;
    boolean goal;
    float goalX;
    float goalY;
    int travel;
    double total;
    ArrayList<String> directions;
    
    private Node(float x, float y, Node previous, String direction, double distance) {
        this.x = x;
        this.y = y;
        this.previous = previous;
        this.direction = direction;
        this.distance = distance;
    }
    
    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }
    
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    
    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public void setGoal(boolean goal) {
        this.goal = goal;
    }
    
    public void setGoalX(float goalX) {
        this.goalX = goalX;
    }
    
    public void setGoalY(float goalY) {
        this.goalY = goalY;
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
    
    public String getDirection() {
        return direction;
    }
    
    public ArrayList<String> getDirections() {
        return directions;
    }
    
    public float getGoalX() {
        return goalX;
    }
    
    public float getGoalY() {
        return goalY;
    }
    
    public Node() {
    }
    
    public Node(float x, float y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    public Node(float x, float y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
    
    Node(boolean b, float x, float y) {
        this.goal = b;
        this.goalX = x;
        this.goalY = y;
    }
    
    Node(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public int getTravel() {
        return this.travel;
    }
    
    public double getTotal() {
        return this.total;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public double calcDistance(float x1, float y1, float x2, float y2) {
        double d = Math.sqrt(Math.abs(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        
        return d;
    }
    
    public ArrayList<String> getPath(Node n) {
        ArrayList<String> s = new ArrayList<>();
        
        s.add(n.getDirection());
        
        while (n.getPrevious() != null) {
            n = n.getPrevious();
            s.add(n.getDirection());
        }
        
        return s;
    }
    
    ArrayList<Node> explored = new ArrayList<>();
    
    public ArrayList<Node> expand(Node current, Node goal) {
        ArrayList<Node> ways = new ArrayList<>();
        
        ArrayList<Node> neighbours = getNeighbours(current);
        
        for (Node neighbour : neighbours) {
            
            if (!explored.contains(neighbour)) {
                Node w = new Node(neighbour.getX(), neighbour.getY(), current, neighbour.direction,
                        calcDistance(neighbour.getX(), neighbour.getY(), goal.getGoalX(), goal.getGoalY()));
                ways.add(w);
                explored.add(neighbour);
                
            } else {
                System.out.println("already exist");
                System.out.println("x: " + neighbour.getX());
                System.out.println("y: " + neighbour.getY());
            }
            
        }
        return ways;
    }
    
    public ArrayList<Node> getNeighbours(Node n) {
        ArrayList<Node> nA = new ArrayList<>();

        ArrayList<String> adj = n.getDirections();
        
        for (int i = 0; i < adj.size(); i++) {
            Node a = new Node(n.getX(), n.getY(), adj.get(i));

            a.setCoordinates(a, a.getDirection());

            nA.add(a);
        }
        return nA;
    }
    
    public void setCoordinates(Node n, String direction) {
        switch (direction) {
            case "Right":
                n.setX(n.getX() + 2);
                break;
            case "Left":
                n.setX(n.getX() - 2);
                break;
            case "Up":
                n.setY(n.getY() + 2);
                break;
            case "Down":
                n.setY(n.getY() - 2);
                break;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).getX() == this.getX() && ((Node) obj).getY() == this.getY();
        }
        return false;
    }
    
}

class CompareDistance implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.getDistance(), n2.getDistance());
    }
}
