package org.greentracker.scene.controllers.authControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.builders.SessionBuilder;
import org.greentracker.builders.UserBuilder;
import org.greentracker.requests.SessionRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class InscriptionController {

    public ImageView imageView;
    public Text nameText;
    public Text emailText;
    public Text passwordText;
    public TextField nameTextField;
    public TextField emailTextField;
    public PasswordField passwordField;
    public Button validateButton;
    public Button returnButton;

    private Stage stage;
    private Scene scene;

    public void switchToMainWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        Parent mainWindow = FXMLLoader.load(new URL(resourcesPath + "MainWindow.fxml"));
        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(mainWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void inscription(ActionEvent actionEvent) throws IOException {

        String[] userInfo = new String[3];
        userInfo[0] = nameTextField.getText();
        userInfo[1] = emailTextField.getText();
        userInfo[2] = passwordField.getText();

        String userSubscription = SessionRequest.UserSubscription(userInfo);

        if (userSubscription != null) {
            String[] userAndSessionInfo = userSubscription.split("},");
            UserBuilder userBuilder = new UserBuilder(userAndSessionInfo[0].replace("{\"user\":{", ""));
            SessionBuilder sessionBuilder = new SessionBuilder(userAndSessionInfo[1].replace("\"session\":{", ""));

            String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
            FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "MenuWindow.fxml"));
            Parent menuWindow = loader.load();

            MenuController menuController = loader.getController();
            menuController.setSessionAndUser(sessionBuilder.getSession(), userBuilder.getUser());

            this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            this.scene = new Scene(menuWindow);
            this.stage.setScene(this.scene);
            this.stage.show();
        }
    }
}
