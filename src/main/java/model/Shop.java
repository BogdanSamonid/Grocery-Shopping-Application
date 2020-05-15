package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Shop {

    private List<Product> shopProducts;
    private StringProperty shopName = new SimpleStringProperty("Lidl");;

    public String getShopName() {
        return shopName.get();
    }

    public StringProperty shopNameProperty() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName.set(shopName);
    }
}
