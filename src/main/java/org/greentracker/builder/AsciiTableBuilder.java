package org.greentracker.builder;

import com.inamik.text.tables.GridTable;
import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;
import org.greentracker.models.State;
import org.greentracker.models.Ticket;
import org.greentracker.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.inamik.text.tables.Cell.Functions.HORIZONTAL_CENTER;

public class AsciiTableBuilder {
    private User user;
    private final List<Ticket> ticketList;
    private final List<State> stateList;

    public AsciiTableBuilder(User user, List<Ticket> ticketList, List<State> stateList) {
        this.user = user;
        this.ticketList = ticketList;
        this.stateList = stateList;
    }

    public void displayTable() {
        SimpleTable table = SimpleTable.of();
        List<String[]> sortedTickets = sortTickets();
        int maxTicketsSize = 0;
        int width = longestStringInArray(sortedTickets);

        for (String[] sortedTicket : sortedTickets) {
            maxTicketsSize = Math.max(sortedTicket.length, maxTicketsSize);
        }

        System.out.println(stateList.size());

        table.nextRow();

        for (State state : stateList) {
            table.nextCell().addLine(state.getName())
                    .applyToCell(HORIZONTAL_CENTER.withWidth(width));

        }

        for (int i = 0; i < maxTicketsSize; i++) {
            table.nextRow();
            if (i < sortedTickets.size()) {
                for (String[] sortedTicket : sortedTickets) {
                    if (i < sortedTicket.length) {
                        table.nextCell().addLine(sortedTicket[i]
                                .replace("[", "")
                                .replace("]", ""))
                                .applyToCell(HORIZONTAL_CENTER.withWidth(width));
                    } else {
                        table.nextCell();
                    }
                }
            }
        }

        GridTable gridTable = table.toGrid();
        gridTable= Border.of(Border.Chars.of('+', '-', '|')).apply(gridTable);

        Util.print(gridTable);
    }

    private List<String[]> sortTickets() {
        List<String[]> sortedTickets = new ArrayList<>();
        for (int i = 0; i < this.stateList.size(); i++) {
            int finalI = i;
            sortedTickets.add(this.ticketList.stream()
                    .filter(ticket -> ticket.getId_state() == this.stateList.get(finalI).getId())
                    .map(Ticket::getName).collect(Collectors.toList()).toString().split(","));
        }
        return sortedTickets;
    }

    private int longestStringInArray(List<String[]> array) {
        int elementLength = array.get(0)[0].length();
        for(int i = 1; i < array.size(); i++) {
            for (String[] arr : array) {
                if (i < arr.length) {
                    if(arr[i].length() > elementLength) {
                        elementLength = arr[i].length();
                    }
                }
            }
        }
        return elementLength;
    }
}
