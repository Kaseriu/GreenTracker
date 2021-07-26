package org.greentracker.scene.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.greentracker.scene.Main;

import java.net.URL;
import java.util.Objects;

public class MainController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button inscriptionButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button connectionButton;

    private Stage stage;
    private Scene scene;

    public void switchToConnectionWindow(ActionEvent actionEvent) {
        try {
            String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
            Parent connectionWindow = FXMLLoader.load(new URL(resourcesPath + "authResources/ConnectionWindow.fxml"));
            this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            this.scene = new Scene(connectionWindow, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            this.stage.setScene(this.scene);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToInscriptionWindow(ActionEvent actionEvent) {
        try {
            String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
            Parent inscriptionWindow = FXMLLoader.load(new URL(resourcesPath + "authResources/InscriptionWindow.fxml"));
            this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            this.scene = new Scene(inscriptionWindow, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            this.stage.setScene(this.scene);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        System.exit(0);
    }
}
