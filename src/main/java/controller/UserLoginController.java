package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginController {

    @FXML
    public void gotoCustomerLogin(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/CustomerLogin.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    @FXML
    public void gotoAdminLogin(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/AdminLogin.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    @FXML
    public void gotoRegister(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserRegisterMainView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    @FXML
    public void gotoShopsList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainShopView.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

}
