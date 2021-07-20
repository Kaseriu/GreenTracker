package org.greentracker.models;

public class Ticket {
    private final int id;
    private final String name;
    private final String description;
    private final int assignee;
    private final int id_user;
    private final int id_state;

    public Ticket(int id, String name, String description, int assignee, int id_user, int id_state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignee = assignee;
        this.id_user = id_user;
        this.id_state = id_state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAssignee() {
        return assignee;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_state() {
        return id_state;
    }

    @Override
    public String toString() {
        return "Informations du Ticket :" +
                "\nId : " + id +
                "\nNom : " + name +
                "\nDescription : " + description +
                "\nAssigner à : " + assignee +
                "\nCréer par : " + id_user +
                "\nEtat actuel : " + id_state;
    }
}
