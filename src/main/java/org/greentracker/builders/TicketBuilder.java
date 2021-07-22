package org.greentracker.builders;

import org.greentracker.models.Ticket;
import org.greentracker.requests.UserRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketBuilder {

    public List<Ticket> ticketList = new ArrayList<>();
    public String allTickets;

    public TicketBuilder(String allTickets) {
        this.allTickets = allTickets;
        setStateList();
    }

    public List<Ticket> getTicketList() {
        return this.ticketList;
    }

    private void setStateList() {
        String[] tickets = this.allTickets.split("},\\{");
        for (String ticket : tickets) {
            String[] tmp = Arrays.toString(ticket.split(",")).replaceAll("[{}\\[\\]]", "").split(",");
            String[] id = tmp[0].split(":");
            String[] name = tmp[1].split(":");
            String[] description = tmp[2].split(":");
            String[] assignee = tmp[3].split(":");
            String[] id_user = tmp[4].split(":");
            String[] id_state = tmp[5].split(":");
            this.ticketList.add(new Ticket(
                    Integer.parseInt(id[1]),
                    name[1].replace("\"", ""),
                    description[1].replace("\"", ""),
                    Integer.parseInt(assignee[1]),
                    Integer.parseInt(id_user[1]),
                    Integer.parseInt(id_state[1])));
        }
    }
}
