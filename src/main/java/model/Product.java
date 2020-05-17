package model;

import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleStringProperty name;
    private SimpleStringProperty price;
    private SimpleStringProperty type;


    public Product(String name, String price, String type){
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.type = new SimpleStringProperty(type);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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
