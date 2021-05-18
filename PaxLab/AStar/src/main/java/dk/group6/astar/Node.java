package dk.group6.astar;

import dk.group6.common.data.World;
import java.util.ArrayList;
import java.util.Comparator;

public class Node {

    Node previous;
    double distance;
    String direction;
    int x;
    int y;
    boolean goal;
    int goalX;
    int goalY;
    int travel;
    double total;
    ArrayList<String> directions;
    
    private Node(int x, int y, Node previous, String direction, double distance) {
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
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setGoal(boolean goal) {
        this.goal = goal;
    }
    
    public void setGoalX(int goalX) {
        this.goalX = goalX;
    }
    
    public void setGoalY(int goalY) {
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
    
    public int getGoalX() {
        return goalX;
    }
    
    public int getGoalY() {
        return goalY;
    }
    
    public Node() {
    }
    
    public Node(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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
    
    public ArrayList<String> getPath(Node n) {
        ArrayList<String> s = new ArrayList<>();
        
        s.add(n.getDirection());
        
        while (n.getPrevious() != null) {
            n = n.getPrevious();
            s.add(n.getDirection());
        }
        
        return s;
    }
    
    
    public ArrayList<Node> expand(Node current, Node goal, World world) {
        ArrayList<Node> ways = new ArrayList<>();
        
        ArrayList<Node> neighbours = getNeighbours(current, world);
        
        for (Node neighbour : neighbours) {
           System.out.println(neighbour.direction); 
            if (!AStar.explored.contains(neighbour)) {
                Node w = new Node(neighbour.getX(), neighbour.getY(), current, neighbour.direction,
                        calcDistance(neighbour.getX(), neighbour.getY(), goal.getGoalX(), goal.getGoalY()));
                w.setTravel(neighbour.getTravel() + 1);
                w.setTotal(w.getTravel() + w.getDistance());
                ways.add(w);
                AStar.explored.add(w);
            } else {
                //System.out.println("already exist");
                //System.out.println("x: " + neighbour.getX());
                //System.out.println("y: " + neighbour.getY());
            }
            
        }
        return ways;
    }
    
    public ArrayList<Node> getNeighbours(Node n, World world) {
        ArrayList<Node> nA = new ArrayList<>();
	
	class position {
		int x;
		int y;
		String direction;
		public position(int x, int y, String direction){
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	ArrayList<position> pos = new ArrayList();       

	pos.add(new position(n.getX()- 10, n.getY(), "left"));
	pos.add(new position(n.getX()+ 10, n.getY(), "right")  );
	pos.add(new position(n.getX(), n.getY() + 10, "up")  );
	pos.add(new position(n.getX(), n.getY() - 10, "down"));
	
	for (position dd : pos) {
		if(world.isValidCell(dd.x, dd.y)){
		    Node a = new Node(dd.x, dd.y, dd.direction );
		    a.setTravel(n.getTravel());
		    a.setCoordinates(a, a.getDirection());

		    nA.add(a);

		}
	}


/*
        for (int i = 0; i < adj.size(); i++) {
            Node a = new Node(n.getX(), n.getY(), adj.get(i));
            a.setTravel(n.getTravel());
            a.setCoordinates(a, a.getDirection());

            nA.add(a);
        }*/
        return nA;
    }
    
    public void setCoordinates(Node n, String direction) {
        switch (direction) {
            case "Right":
                n.setX(n.getX() + 10);
                break;
            case "Left":
                n.setX(n.getX() - 10);
                break;
            case "Up":
                n.setY(n.getY() + 10);
                break;
            case "Down":
                n.setY(n.getY() - 10);
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

class CompareTotal implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.getTotal(), n2.getTotal());
    }
}
