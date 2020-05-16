package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Shop {

    private StringProperty shopName = new SimpleStringProperty("");;

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
