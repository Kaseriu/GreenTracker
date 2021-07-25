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
import org.greentracker.requests.UserRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ConnectionController {

    public ImageView imageView;
    public Text emailText;
    public TextField emailTextField;
    public Text passwordText;
    public PasswordField passwordField;
    public Button validateButton;
    public Text errorText;
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

    public void connection(ActionEvent actionEvent) throws IOException {
        String session = SessionRequest.SessionConnection(emailTextField.getText(), passwordField.getText());

        if (session != null) {
            SessionBuilder sessionBuilder = new SessionBuilder(session);
            String userInfo = UserRequest.GetUserById(sessionBuilder.getSession());
            UserBuilder userBuilder = new UserBuilder(userInfo);

            String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
            FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "MenuWindow.fxml"));
            Parent menuWindow = loader.load();

            MenuController menuController = loader.getController();
            menuController.setSessionAndUser(sessionBuilder.getSession(), userBuilder.getUser());

            this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            this.scene = new Scene(menuWindow);
            this.stage.setScene(this.scene);
            this.stage.show();
        } else {
            this.errorText.setVisible(true);
        }
    }
}
