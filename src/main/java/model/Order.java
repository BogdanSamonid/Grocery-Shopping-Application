package model;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String shopName;
    private List<ShoppingCart> productList=new ArrayList<ShoppingCart>();

    public Order() {}

    public Order(Text shopName, List<ShoppingCart> productList) {
        this.shopName=shopName.getText();
        this.productList.addAll(productList);
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<ShoppingCart> getProductList() {
        return this.productList;
    }

    public void setProductList(List<ShoppingCart> productList) {
        this.productList = productList;
    }

    public boolean equals(Object object) {
        if(object==this)
            return true;

        if(!(object instanceof Order))
            return false;

        Order order=(Order) object;
        return this.shopName.equals(order.shopName) && this.productList.equals(order.productList);
    }
}
