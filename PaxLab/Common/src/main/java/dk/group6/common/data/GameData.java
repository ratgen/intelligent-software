package dk.group6.common.data;

import dk.group6.common.events.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private List<Event> events = new CopyOnWriteArrayList<>();
    private boolean gameIsWon = false;
    private boolean gameIsLost = false;

    public boolean isGameWon() {
        return gameIsWon;
    }

    public void setGameIsWon(boolean gameIsWon) {
        this.gameIsWon = gameIsWon;
    }

    public boolean isGameLost() {
        return gameIsLost;
    }

    public void setGameIsLost(boolean gameIsLost) {
        this.gameIsLost = gameIsLost;
    }


    public void addEvent(Event e) {
        events.add(e);
    }

    public void removeEvent(Event e) {
        events.remove(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID) {
        List<Event> r = new ArrayList();
        for (Event event : events) {
            if (event.getClass().equals(type) && event.getSource().getID().equals(sourceID)) {
                r.add(event);
            }
        }

        return r;
    }
}
