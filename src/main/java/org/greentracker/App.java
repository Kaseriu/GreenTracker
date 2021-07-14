package org.greentracker;

import org.greentracker.builder.AsciiTableBuilder;
import org.greentracker.models.State;
import org.greentracker.models.Ticket;
import org.greentracker.models.User;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        User user = new User(0, "user", "email", "pwd");
        State state0 = new State(0, "A faire");
        State state1 = new State(1, "En cours");
        State state2 = new State(2, "Finit");
        Ticket ticket0 = new Ticket(0, "ticket0", "desc", 1, 0, 0);
        Ticket ticket1 = new Ticket(1, "ticket1", "desc", 1, 0, 1);
        Ticket ticket1b = new Ticket(1, "ticket1b", "desc", 1, 0, 1);
        Ticket ticket2 = new Ticket(2, "ticket2", "desc", 1, 0, 2);

        List<State> stateList = new ArrayList<>();
        stateList.add(state0);
        stateList.add(state1);
        stateList.add(state2);

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket0);
        ticketList.add(ticket1);
        ticketList.add(ticket1b);
        ticketList.add(ticket2);

        AsciiTableBuilder builder = new AsciiTableBuilder(user, ticketList, stateList);
        builder.displayTable();
    }
}

//TODO :
// - menu
// - relier a l'API
// - connection
