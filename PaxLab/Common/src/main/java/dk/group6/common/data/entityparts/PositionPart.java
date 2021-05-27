/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import java.util.ArrayList;

/**
 *
 */
public class PositionPart implements EntityPart {

    private int x;
    private int y;
    private double radians;

    private ArrayList<String> directions = new ArrayList();

    public PositionPart(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> a) {
        this.directions = a;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRadians() {
        return radians;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
