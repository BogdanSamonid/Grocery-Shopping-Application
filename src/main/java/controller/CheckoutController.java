package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Product;
import model.Shop;
import model.ShoppingCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutController<prList> {

    public TableView<ShoppingCart> table;
    public TableColumn<ShoppingCart, String> prName;
    public TableColumn<ShoppingCart, String> prPrice;

    public void gotoShopsList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ShopsListView.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public void init(List<ShoppingCart> cart){

        ObservableList<ShoppingCart> data = FXCollections.observableArrayList(cart);
        table.setEditable(true);
        prName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(data);
    }



}
