package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Product;
import model.Shop;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.DataStore;
import service.JsonParser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ShopPageController{

    @FXML
    public Text currentShop;
    public TableView table;
    public TableColumn prName;
    public TableColumn prPrice;

    private JsonParser jsonParser;

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
        try {
            jsonParser = new JsonParser();
            JSONObject obj = jsonParser.parse("/datastorage/shop_products.json");
            JSONArray products = (JSONArray) obj.get(shop.getShopName());
            Iterator<JSONObject> it = products.iterator();
            List<Product> prList = new ArrayList<Product>();

            while(it.hasNext()){
                JSONObject getProduct = it.next();
                prList.add(new Product(getProduct.get("name").toString(), (String) getProduct.get("price")));
            }

            ObservableList<Product> data = FXCollections.observableArrayList(prList);
            table.setEditable(true);
            prName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
            prPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
            table.setItems(data);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
