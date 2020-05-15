package service;


/**
 * Usage:
 *
 * DataStore ds = DataStore.INSTANCE;
 * ds.getShopName();
 *
 */
public class DataStore {

    public static DataStore INSTANCE = new DataStore();
    private String currentShop;

    private DataStore(){}

    public String getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(String currentShop) {
        this.currentShop = currentShop;
    }
}
