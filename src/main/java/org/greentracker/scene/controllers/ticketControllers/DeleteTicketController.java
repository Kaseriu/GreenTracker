package org.greentracker.scene.controllers.ticketControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.TicketRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class DeleteTicketController {

    @FXML
    private ImageView imageView;
    @FXML
    private Text ticketToDeleteNameText;
    @FXML
    private TextField ticketToDeleteNameTextField;
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

    public void validateButton() {
        if (TicketRequest.getTicketByName(this.session, this.ticketToDeleteNameTextField.getText()) != null) {
            TicketRequest.deleteTicket(this.session, this.ticketToDeleteNameTextField.getText());
            this.validateText.setVisible(true);
            this.ticketToDeleteNameTextField.clear();
        } else {
            this.ticketToDeleteNameTextField.clear();
            this.ticketToDeleteNameTextField.setPromptText("Aucun ticket de ce nom");
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
}
