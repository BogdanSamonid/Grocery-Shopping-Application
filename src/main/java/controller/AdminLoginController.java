package controller;

import exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AdminService;

import java.io.IOException;

public class AdminLoginController {

    private AdminService adminService;

    @FXML
    public TextField passwordField;
    @FXML
    public TextField employeeIDField;
    @FXML
    public TextField usernameField;

    public AdminLoginController(){
        adminService = new AdminService();
    }

    @FXML
    public void gotoUserLogin(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserLoginMainView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    @FXML
    public void loginButtonAction(ActionEvent event) throws IOException, WrongIDException, WrongPasswordException, WrongUsernameException, EmptyIDException, EmptyPasswordException, EmptyUsernameException {
        try {
            adminService.checkAdmin(usernameField.getText(), employeeIDField.getText(), passwordField.getText());
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Logged in successfuly!", ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();

            Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainAdminPageView.fxml"));
            Scene view2 = new Scene(view);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(view2);
            window.show();

        }
        catch (WrongUsernameException | WrongPasswordException | WrongIDException | EmptyUsernameException | EmptyPasswordException |EmptyIDException e) {
            Alert alert=new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        }

    }
}
