package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerService {

    private static List<Customer> customers=new ArrayList<Customer>();

    public static void loadCustomersFromFile() { /*LOAD THE LIST WITH JSON(ADMIN) OBJECTS*/
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("C:/Users/User/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/customer.json"));
            TypeReference<List<Customer>> typeReference = new TypeReference<List<Customer>>() {
            };
            customers = mapper.readValue(inputStream, typeReference);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(String username, String password) throws UsernameAlreadyExistsException, EmptyPasswordException, EmptyUsernameException{
        loadCustomersFromFile();
        checkCustomerDoesNotExist(username);
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        Customer newCustomer = new Customer(username, encodePassword(username, password));
        customers.add(newCustomer);
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file=new File("C:/Users/User/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/customer.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void checkCustomer(String username, String password) throws EmptyPasswordException, EmptyUsernameException, WrongPasswordException, WrongUsernameException {
        loadCustomersFromFile();
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        checkAccount(username, password);
    }

    private static void checkAccount(String username, String password) throws WrongPasswordException, WrongUsernameException {
        int ok = 0;
        for (Customer customer : customers) {
            if(Objects.equals(username, customer.getUsername()))
            {
                ok=1;

                if(Objects.equals(encodePassword(username, password), customer.getPassword()))
                    ok=2;
            }
        }
        if(ok==0)
            throw new WrongUsernameException();
        if(ok==1)
            throw new WrongPasswordException();
    }

    private static void checkCustomerDoesNotExist(String username) throws UsernameAlreadyExistsException{

        for (Customer customer : customers) {
            if (Objects.equals(username, customer.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkUsernameIsNotEmpty(String username)throws EmptyUsernameException {

        if(Objects.equals(username, ""))
            throw new EmptyUsernameException(username);
    }

    private static void checkPasswordIsNotEmpty(String password)throws EmptyPasswordException {

        if(Objects.equals(password,""))
            throw new EmptyPasswordException(password);
    }

    private static void persistCustomers() { /*WRITE THE LIST BACK IN THE JSON FILE*/

    }

    private static String encodePassword(String salt, String password) {

        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-512");

        } catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException("SHA-512 does not exist!");
        }

        return md;
    }
}
