package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;

import java.io.IOException;

public class AdminRegisterController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField employeeIDField;
    @FXML
    private TextField passwordField;

    public void gotoUserRegister(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserRegisterMainView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    public void registerAdmin() throws IOException {

        Admin admin=new Admin();
        admin.setUsername(usernameField);
        admin.setID(employeeIDField);
        admin.setPassword(passwordField);

    }

}
