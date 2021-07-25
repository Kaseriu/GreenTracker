package org.greentracker.scene.controllers.ticketControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.TicketRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class CreateTicketController {
    public Text title;
    public Button validateButton;
    public Button returnButton;
    public Text ticketNameText;
    public Text descriptionText;
    public Text assignedToText;
    public Text validateText;
    public TextField ticketNameTextField;
    public TextField descriptionTextField;
    public TextField assignedToTextField;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
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

    public void validate() {
        String[] newTicketInfo = new String[3];
        newTicketInfo[0] = this.ticketNameTextField.getText();
        newTicketInfo[1] = this.descriptionTextField.getText();
        newTicketInfo[2] = this.assignedToTextField.getText();

        TicketRequest.createTicket(this.session, this.user, newTicketInfo);
        validateText.setVisible(true);
    }
}
