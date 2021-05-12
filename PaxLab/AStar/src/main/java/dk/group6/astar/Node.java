package dk.group6.astar;

import java.util.ArrayList;
import java.util.Comparator;

public class Node{
    ArrayList<Node> ways = new ArrayList<>();
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

    public float getGoalX() {
        return goalX;
    }

    public float getGoalY() {
        return goalY;
    }
    
    ArrayList<Node> explored = new ArrayList<>();
    
    public Node () {
    }
    
    public Node (float x, float y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    public Node (float x, float y, double distance) {
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
    
    public int getTravel(){
        return this.travel;
    }
    
    public double getTotal (){
        return this.total;
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getY(){
        return this.y;
    }
    
    public double calcDistance (float x1, float x2, float y1, float y2) {
        double d = Math.sqrt(Math.abs(Math.pow(x2 - x1, 2)+Math.pow(y2 - y1, 2)));
         
        return d;
    }
    
    public String[] getPath (Node n){
        String[] s = new String[50];
        
        s[0] = n.getDirection();
        
        for (int i = 1; i < 50; i++) {
            if(n.getPrevious()!=null){
                n = n.getPrevious();
                s[i] = n.getDirection();
            }else {
                return s;
            }
        }
        return s;
    }
    
    public ArrayList<Node> expand(Node n){
        Node[] neighbours = getNeighbours(n);
        int i = 0;
        
        for (Node neighbour : neighbours) {
            if (!explored.contains(neighbour)){
                Node w = new Node();
                w.setX(neighbour.getX());
                w.setY(neighbour.getY());
                w.setPrevious(n);
                w.setDirection(neighbour.direction);
                w.setDistance(calcDistance(w.getX(), w.getY(), getGoalX(), getGoalY()));
                w.setTravel(1);
                w.setTotal(w.getDistance()+w.getTravel());
                ways.add(w);
                explored.add(neighbour);
//                System.out.println("check2");
                i++;
            }
        }
        return ways;
    }
    
    
    public Node[] getNeighbours (Node n){
        Node[] nA = new Node[4];
        
        String[] adj = getAdjacent(n);
        
        for (int i = 0; i < adj.length; i++) {
            nA[i] = new Node(n.getX(), n.getY(), adj[i]);
            nA[i].setCoordinates(n, adj[i]);
//            System.out.println("check1");
        }
        return nA;
    }
    
    
    public String[] getAdjacent (Node n) {
        String[] sA = {"Left", "Down", "Up", "Right"};
//        System.out.println("check4");
        return sA;
    }
    
    
    public void setCoordinates(Node n, String direction){
//        System.out.println("check5");
        switch (direction){
            case "Right":
                n.setX(n.getX()+1);
                break;
            case "Left":
                n.setX(n.getX()-1);
                break;
            case "Up":
                n.setY(n.getY()+1);
                break;
            case "Down":
                n.setY(n.getY()-1);
                break;
        }
    }
}

class CompareDistance implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.distance, n2.distance);
    }
}