package org.greentracker.scene.controllers.ticketControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.builders.TicketBuilder;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.TicketRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ModifyTicketController {

    @FXML
    private ImageView imageView;
    @FXML
    private Text ticketToUpdateNameText;
    @FXML
    private TextField ticketToUpdateNameTextField;
    @FXML
    private Button validateTicketToUpdateNameButton;
    @FXML
    private Text ticketNameText;
    @FXML
    private TextField ticketNameTextField;
    @FXML
    private Text descriptionText;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Text assignedToText;
    @FXML
    private TextField assignedToTextField;
    @FXML
    private Text stateText;
    @FXML
    private TextField stateTextField;
    @FXML
    private Button validateButton;
    @FXML
    private Text validateText;
    @FXML
    private Button returnButton;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public void validateTicketToUpdateName() {
        String ticketToUpdateInfo = TicketRequest.getTicketByName(this.session, ticketToUpdateNameTextField.getText());
        if (ticketToUpdateInfo != null) {
            this.ticketToUpdateNameText.setVisible(false);
            this.ticketToUpdateNameTextField.setVisible(false);
            this.validateTicketToUpdateNameButton.setVisible(false);

            this.ticketNameText.setVisible(true);
            this.ticketNameTextField.setVisible(true);
            this.descriptionText.setVisible(true);
            this.descriptionTextField.setVisible(true);
            this.assignedToText.setVisible(true);
            this.assignedToTextField.setVisible(true);
            this.stateText.setVisible(true);
            this.stateTextField.setVisible(true);
            this.validateButton.setVisible(true);
        } else {
            this.ticketToUpdateNameTextField.clear();
            this.ticketToUpdateNameTextField.setPromptText("Aucun ticket de ce nom");
        }
    }

    public void validate() {
        TicketBuilder ticketToUpdateBuilder =
                new TicketBuilder(TicketRequest.getTicketByName(this.session, this.ticketToUpdateNameTextField.getText()));
        String[] updateTicketInfo = new String[4];
        updateTicketInfo[0] = this.ticketNameTextField.getText();
        updateTicketInfo[1] = this.descriptionTextField.getText();
        updateTicketInfo[2] = this.assignedToTextField.getText();
        updateTicketInfo[3] = this.stateTextField.getText();

        TicketRequest.updateTicket(
                session, ticketToUpdateBuilder.getTicketList().get(0).getName(), updateTicketInfo);

        this.validateText.setVisible(true);
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
}
