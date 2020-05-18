package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Product;
import model.Shop;
import model.ShoppingCart;
import org.msgpack.value.IntegerValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutController<prList> {

    public TableView<ShoppingCart> table;
    public TableColumn<ShoppingCart, String> prName;
    public TableColumn<ShoppingCart, String> prPrice;
    public TextField total;


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
        totalPrice(table,total);

    }

    public void totalPrice(TableView<ShoppingCart> table, TextField total ){
        float totalSavingValue=0;
        for (ShoppingCart value : table.getItems()) {
            totalSavingValue = totalSavingValue + Float.parseFloat(value.getPrice());
        }

        total.setText(String.valueOf(totalSavingValue) + " ron");
        total.setAlignment(Pos.CENTER);
        total.setDisable(true);
    }


}
