package dk.group6.astar;

import dk.group6.common.data.World;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Node {
  Node previous;
  double distance;
  double radians;
  int x;
  int y;
  boolean goal;
  int goalX;
  int goalY;
  int travel;
  double total;

  public void setPrevious(Node previous) {
    this.previous = previous;
  }

  public void setDistance(double distance) {
    this.distance = distance;
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

  public double getDirection (Node current, Node newNode){

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

  public ArrayList<Node> getNeighbours(Node n, World world) {
    ArrayList<Node> nA = new ArrayList<>();

    ArrayList<Node> pos = new ArrayList();       

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
        nA.add(node);
      }
    }

    return nA;
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
