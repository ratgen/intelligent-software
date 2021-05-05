package dk.group6.astar;

import java.util.ArrayList;
import java.util.Map;

public class Node {
    Map<String, Integer> coordinates;
    Node previous;
    double distance;
    String direction;
    
    int travel;
    double total;
    
    ArrayList<Node> explored = new ArrayList<>();
    
    public Node () {
    }
    
    public Node (Map<String, Integer> coordinates, String direction) {
    this.coordinates = coordinates;
    this.direction = direction;
    }
    
    public Node (Map<String, Integer> coordinates, double distance) {
    this.coordinates = coordinates;
    this.distance = distance;
    }
    
    
    public double getTotal (){
        return this.total;
    }
    
    public double calcDistance (Map<String, Integer> e, Map<String, Integer> p) {
        double d = Math.sqrt(Math.pow((e.get("x")-e.get("y")),2)+Math.pow((p.get("x")-p.get("y")), 2));
         
        return d;
    }
    
    public String[] getPath (Node n){
        String[] s = new String[50];
        
        s[0] = n.direction;
        
        for (int i = 1; i < 50; i++) {
            if(n.previous!=null){
                n = n.previous;
                s[i] = n.direction;
            }else {
                return s;
            }
        }
        return s;
    }
    
    public Node[] expand(Node n, Map<String, Integer> goal){
        Node[] ways = new Node[4];
        Node[] neighbours = getNeighbours(n);
        
        for (Node neighbour : neighbours) {
            if (!explored.contains(neighbour)){
                int i = 0;
                Node w = new Node();
                w.previous = n;
                w.direction = neighbour.direction;
                w.distance = calcDistance(neighbour.coordinates, goal);
                w.travel = 1;
                w.total = w.distance + w.travel;
                ways[i] = w;
                explored.add(neighbour);
                i++;
            }
        }
        return ways;
    }
    
    
    public Node[] getNeighbours (Node n){
        Node[] nA = new Node[4];
        
        String[] adj = getAdjacent(n);
        
        for (int i = 0; i < adj.length; i++) {
            nA[i] = new Node(n.setCoordinates(n.coordinates, adj[i]), adj[i]);
        }
        return nA;
    }
    
    
    public String[] getAdjacent (Node n) {
        String[] sA = {"Left", "Down", "Up", "Right"};
        return sA;
    }
    
    
    public Map<String, Integer> setCoordinates(Map<String, Integer> m, String direction){
        int x = m.get("x");
        int y = m.get("y");
        switch (direction){
            case "Right":
                m.replace("x", x+1);
                return m;
            case "Left":
                m.replace("x", x-1);
                return m;
            case "Up":
                m.replace("y", y+1);
                return m;
            case "Down":
                m.replace("y", y-1);
                return m;
        }
        return m;
    }
}
