package org.greentracker.scene.controllers.stateControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.builders.StateBuilder;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.StateRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ModifyStateController {

    public Text title;
    public Text stateToUpdateNameText;
    public TextField stateToUpdateNameTextField;
    public Button validateStateToUpdateNameButton;
    public Text stateNameText;
    public TextField stateNameTextField;
    public Button validateButton;
    public Text validateText;
    public Button returnButton;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public void validateStateToUpdateName() {
        String stateToUpdateInfo = StateRequest.getStateByName(this.session, this.stateToUpdateNameTextField.getText());
        if (stateToUpdateInfo != null) {
            this.stateToUpdateNameText.setVisible(false);
            this.stateToUpdateNameTextField.setVisible(false);
            this.validateStateToUpdateNameButton.setVisible(false);

            this.stateNameText.setVisible(true);
            this.stateNameTextField.setVisible(true);
            this.validateButton.setVisible(true);
        } else {
            this.stateToUpdateNameTextField.clear();
            this.stateToUpdateNameTextField.setPromptText("Aucun état de ce nom");
        }
    }

    public void validate() {
        StateBuilder stateToUpdateBuilder =
                new StateBuilder(StateRequest.getStateByName(this.session, this.stateToUpdateNameTextField.getText()));

        StateRequest.updateState(
                session, stateToUpdateBuilder.getStateList().get(0).getName(), this.stateNameTextField.getText());

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
