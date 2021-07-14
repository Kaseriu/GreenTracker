package org.greentracker.models;

public class Ticket {
    private int id;
    private String name;
    private String description;
    private int assignee;
    private int id_user;
    private int id_state;

    public Ticket(int id, String name, String description, int assignee, int id_user, int id_state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignee = assignee;
        this.id_user = id_user;
        this.id_state = id_state;
    }

    public String getName() {
        return name;
    }

    public int getId_state() {
        return id_state;
    }
}
