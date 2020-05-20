package controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Order;
import model.ShoppingCart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminViewOrdersController{

    @FXML
    ComboBox<String> comboBox;
    @FXML
    ListView<List<ShoppingCart>> currentOrders;

    //private static List<Order> orders = new ArrayList<Order>();

   /* public void initialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("C:/Users/Oana Tomuta/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/order.json"));
            TypeReference<List<Order>> typeReference = new TypeReference<List<Order>>() {
            };
            orders = mapper.readValue(inputStream, typeReference);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


   /* public void init(){
        initialize();


        this.comboBox.setPromptText("[Select Supermarket]");
        this.comboBox.setOnAction(e -> comboBoxAction(comboBox.getValue()));
    }*/

    /*private void comboBoxAction(String supermarketName) {
       /* for (Order order : orders) {
            if (order.getShopName().equals(supermarketName))
            //  currentOrders.add
        }*/
    //}

    public void gotoMainAdminPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainAdminPageView.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }
}