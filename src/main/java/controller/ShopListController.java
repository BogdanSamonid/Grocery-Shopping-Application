package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.JsonParser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ShopListController implements Initializable {

    @FXML
    public Button myButton;

    //public List<TextField> vboxTextField;

    @FXML
    public VBox shopList;

    private JsonParser jsonParser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
                jsonParser = new JsonParser();
                JSONObject obj = jsonParser.parse("/datastorage/list_of_grocery_shops.json");
                JSONArray shopNames = (JSONArray) obj.get("Grocery_Shops");
                Iterator it = shopNames.iterator();
                while(it.hasNext()){
                    String element = (String) it.next();
                    System.out.println("Read "+ element);
                    TextField tf = new TextField();
                    tf.setText(element);
                    shopList.getChildren().add(tf);
                }

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }

        for( Node i : shopList.getChildren()) {

            i.setOnMouseClicked(new LabelEventHandler());
        }

        myButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(actionEvent.toString());
            }
        });
    }
}
