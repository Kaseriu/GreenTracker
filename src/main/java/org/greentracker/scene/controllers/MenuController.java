package org.greentracker.scene.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.SessionRequest;
import org.greentracker.scene.controllers.stateControllers.CreateStateController;
import org.greentracker.scene.controllers.stateControllers.DeleteStateController;
import org.greentracker.scene.controllers.stateControllers.ModifyStateController;
import org.greentracker.scene.controllers.ticketControllers.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MenuController {

    @FXML
    private ImageView imageView;
    @FXML
    private Text helloText;
    @FXML
    private Button ticketsButton;
    @FXML
    private Button myTicketsButton;
    @FXML
    private Button infoTicketButton;
    @FXML
    private Button createTicketButton;
    @FXML
    private Button modifyTicketButton;
    @FXML
    private Button deleteTicketButton;
    @FXML
    private Button createStateButton;
    @FXML
    private Button modifyStateButton;
    @FXML
    private Button deleteStateButton;
    @FXML
    private Button disconnectButton;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;

    public MenuController() {
    }

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }


    public void switchToDisplayAllTickets(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "ticketResources/DisplayAllTicketsWindow.fxml"));
        Parent displayAllTicketsWindow = loader.load();

        DisplayAllTicketsController displayAllTicketsController = loader.getController();
        displayAllTicketsController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(displayAllTicketsWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToDisplayUserTickets(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "ticketResources/DisplayUserTicketsWindow.fxml"));
        Parent displayUserTicketsWindow = loader.load();

        DisplayUserTicketsController displayUserTicketsController = loader.getController();
        displayUserTicketsController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(displayUserTicketsWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToDisplayTicketInfo(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "ticketResources/DisplayTicketInfoWindow.fxml"));
        Parent displayTicketInfoWindow = loader.load();

        DisplayTicketInfoController displayTicketInfoController = loader.getController();
        displayTicketInfoController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(displayTicketInfoWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToCreateTicketWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "ticketResources/CreateTicketWindow.fxml"));
        Parent createTicketWindow = loader.load();

        CreateTicketController createTicketController = loader.getController();
        createTicketController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(createTicketWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToModifyTicketWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "ticketResources/ModifyTicketWindow.fxml"));
        Parent modifyTicketWindow = loader.load();

        ModifyTicketController modifyTicketController = loader.getController();
        modifyTicketController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(modifyTicketWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToDeleteTicketWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "ticketResources/DeleteTicketWindow.fxml"));
        Parent deleteTicketWindow = loader.load();

        DeleteTicketController deleteTicketController = loader.getController();
        deleteTicketController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(deleteTicketWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToCreateStateWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "stateResources/CreateStateWindow.fxml"));
        Parent createTicketWindow = loader.load();

        CreateStateController createStateController = loader.getController();
        createStateController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(createTicketWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToModifyStateWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "stateResources/ModifyStateWindow.fxml"));
        Parent modifyTicketWindow = loader.load();

        ModifyStateController modifyStateController = loader.getController();
        modifyStateController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(modifyTicketWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToDeleteStateWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "stateResources/DeleteStateWindow.fxml"));
        Parent deleteStateWindow = loader.load();

        DeleteStateController deleteStateController = loader.getController();
        deleteStateController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(deleteStateWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void switchToMainWindow(ActionEvent actionEvent) throws IOException {
        SessionRequest.SessionDisconnection(this.session);
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        Parent mainWindow = FXMLLoader.load(new URL(resourcesPath + "MainWindow.fxml"));
        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(mainWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
