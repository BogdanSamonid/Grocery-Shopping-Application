package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Shop;
import service.DataStore;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopPageController {

    @FXML
    public Text currentShop;

    public ShopPageController() {
    }

    public void gotoShopsList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ShopsListView.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public void init(Shop shop){
        currentShop.setText(shop.getShopName());
    }

}
