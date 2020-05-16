package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class ChooseShopEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Parent view = null;
        try {
            view = FXMLLoader.load(getClass().getClassLoader().getResource("view/ShopView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene view2 = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }
}
