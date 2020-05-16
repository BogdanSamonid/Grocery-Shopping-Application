package controller;

import exceptions.EmptyIDException;
import exceptions.EmptyPasswordException;
import exceptions.EmptyUsernameException;
import exceptions.UsernameAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AdminService;

import java.io.IOException;

public class AdminRegisterController {

    @FXML
    private TextField usernameField;
    @FXML
    public TextField employeeIDField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField messageField;

    public void gotoUserRegister(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserRegisterMainView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    public void gotoAdminLogin(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/AdminLogin.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    public void registerButtonAction() {

        try {

            AdminService.addAdmin(usernameField.getText(), employeeIDField.getText(), passwordField.getText());
            messageField.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {

            messageField.setText(e.getMessage());
        } catch (EmptyPasswordException passwordEmpty) {

            passwordEmpty.printStackTrace();
        } catch (EmptyIDException IDEmpty) {

            IDEmpty.printStackTrace();
        } catch (EmptyUsernameException userEmpty) {

            userEmpty.printStackTrace();
        }

    }

}
