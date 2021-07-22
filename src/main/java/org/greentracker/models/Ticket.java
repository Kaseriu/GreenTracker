package org.greentracker.models;

import org.greentracker.requests.StateRequest;
import org.greentracker.requests.UserRequest;

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

    public String toString(Session session) throws Exception {
        return "Informations du Ticket :" +
                "\nId : " + this.id +
                "\nNom : " + this.name +
                "\nDescription : " + this.description +
                "\nAssigner à : " + UserRequest.GetUserName(session, this.assignee) +
                "\nCréer par : " + UserRequest.GetUserName(session, this.id_user) +
                "\nEtat actuel : " + StateRequest.GetStateName(session, this.id_state);
    }
}
