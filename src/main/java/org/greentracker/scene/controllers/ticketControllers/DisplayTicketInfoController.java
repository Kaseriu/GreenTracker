package org.greentracker.scene.controllers.ticketControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class DisplayTicketInfoController {


    public Text title;
    public Text ticketNameText;
    public TextField ticketNameTextField;
    public Button validateButton;
    public TextArea ticketTextArea;
    public Button returnButton;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public void validate() throws Exception {
        String ticketInfo = TicketRequest.getTicketByName(this.session, this.ticketNameTextField.getText());
        if (ticketInfo != null) {
            this.ticketNameText.setVisible(false);
            this.ticketNameTextField.setVisible(false);
            this.validateButton.setVisible(false);

            this.ticketTextArea.setVisible(true);

            TicketBuilder ticketByNameBuilder = new TicketBuilder(ticketInfo);
            this.ticketTextArea.setText(ticketByNameBuilder.getTicketList().get(0).toString(this.session));

        } else {
            this.ticketNameTextField.clear();
            this.ticketNameTextField.setPromptText("Aucun ticket de ce nom");
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
