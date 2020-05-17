package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.JsonParser;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ShopListController implements Initializable {

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

            while (it.hasNext()) {
                String element = (String) it.next();

                Button b = new Button();
                //b.setEditable(false); //makes textfield not editable
                b.setMaxWidth(Double.MAX_VALUE);
                b.setText(element);
                b.setAlignment(Pos.CENTER);
                b.setStyle("-fx-background-color: plum; -fx-background-radius: 15px; -fx-text-fill: black");
                shopList.getChildren().add(b);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for(Node i: shopList.getChildren()) {
            Button b = (Button) i;
            b.setOnAction(new ChooseShopEventHandler());
        }

    }
}
