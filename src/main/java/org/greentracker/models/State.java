package org.greentracker.models;

public class State {
    private int id;
    private String name;

    public State(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
