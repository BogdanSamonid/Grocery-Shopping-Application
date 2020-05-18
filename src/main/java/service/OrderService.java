package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OrderService {
    public static void addOrder(Order order) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file=new File("C:/Users/User/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/order.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, order);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
