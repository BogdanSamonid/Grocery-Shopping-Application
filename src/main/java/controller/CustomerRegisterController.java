package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class CustomerRegisterController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    public void gotoUserRegister(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserRegisterMainView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

}
