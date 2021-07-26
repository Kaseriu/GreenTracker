package org.greentracker.scene.controllers.ticketControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.greentracker.builders.StateBuilder;
import org.greentracker.builders.TicketBuilder;
import org.greentracker.models.Session;
import org.greentracker.models.State;
import org.greentracker.models.Ticket;
import org.greentracker.models.User;
import org.greentracker.requests.StateRequest;
import org.greentracker.requests.TicketRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DisplayUserTicketsController {

    @FXML
    private ImageView imageView;
    @FXML
    private TextArea ticketsTextArea;
    @FXML
    private Button returnButton;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;
    private List<Ticket> ticketList;
    private List<State> stateList;

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
        displayUserTickets();
    }

    public void displayUserTickets() {
        List<String[]> sortedTickets = sortTickets();
        List<List<String>> cells = new ArrayList<>();
        int maxTicketsSize = 0;

        for (String[] sortedTicket : sortedTickets) {
            maxTicketsSize = Math.max(sortedTicket.length, maxTicketsSize);
        }

        for (State state : this.stateList) {
            List<String> cell = new ArrayList<>();
            cell.add(state.getName());
            cells.add(cell);
        }

        for (Ticket ticket : this.ticketList) {
            for (List<String> cell : cells) {
                if (cell.get(0).equals(ticket.getIdStateName(this.session))) {
                    cell.add(ticket.getName());
                }
            }
        }

        for (List<String> cell : cells) {
            this.ticketsTextArea.appendText(cell.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replaceFirst(",", " : "));
            this.ticketsTextArea.appendText("\n");
        }
    }

    public void switchToMenuWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "MenuWindow.fxml"));
        Parent menuWindow = loader.load();

        MenuController menuController = loader.getController();
        menuController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(menuWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    private List<String[]> sortTickets() {
        StateBuilder stateBuilder = new StateBuilder(StateRequest.getAllStates(this.session));
        TicketBuilder ticketBuilder = new TicketBuilder(TicketRequest.getUserTickets(this.session, this.user));
        this.stateList = stateBuilder.getStateList();
        this.ticketList = ticketBuilder.getTicketList();

        List<String[]> sortedTickets = new ArrayList<>();
        for (int i = 0; i < this.stateList.size(); i++) {
            int finalI = i;
            sortedTickets.add(this.ticketList.stream()
                    .filter(ticket -> ticket.getId_state() == this.stateList.get(finalI).getId())
                    .map(Ticket::getName).collect(Collectors.toList()).toString().split(","));
        }
        return sortedTickets;
    }
}
