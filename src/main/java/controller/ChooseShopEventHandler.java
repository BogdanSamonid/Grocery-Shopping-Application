package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Shop;

import java.io.IOException;


public class ChooseShopEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ShopView.fxml"));
            Parent view = loader.load();
            Scene view2 = new Scene(view);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(view2);
            ShopPageController controller = loader.getController();
            Shop shop = new Shop();
            shop.setShopName(((Button)event.getTarget()).getText());
            controller.init(shop);

            window.show();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
