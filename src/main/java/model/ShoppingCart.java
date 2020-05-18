package model;

import javafx.beans.property.SimpleStringProperty;

public class ShoppingCart {

    private SimpleStringProperty name;
    private SimpleStringProperty price;

    public ShoppingCart(String name, String price){
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
