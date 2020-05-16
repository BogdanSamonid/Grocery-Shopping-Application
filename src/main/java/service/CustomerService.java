package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Customer;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class CustomerService {

    private static List<Customer> customers;
    public static final Path CUSTOMER_PATH=FileSystem.getPathToFile("config", "customers.json");


    public static void loadCustomersFromFile() throws IOException {
        if (!Files.exists(CUSTOMER_PATH)) {
            FileUtils.copyURLToFile(CustomerService.class.getClassLoader().getResource("customers.json"), CUSTOMER_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        customers = objectMapper.readValue(CUSTOMER_PATH.toFile(),
                new TypeReference<List<Customer>> () {
        });
    }

    public static void addCustomer(String username, String password) throws UsernameAlreadyExistsException, EmptyPasswordException, EmptyUsernameException {
        checkCustomerDoesNotExist(username);
        checkUsernameIsNotEmpty(username);
        checkPasswordIsNotEmpty(password);
        customers.add(new Customer(username, encodePassword(username, password)));
        persistCustomers();
    }

    public static void checkCustomer(String username, String password) throws EmptyPasswordException, EmptyUsernameException, WrongPasswordException, WrongUsernameException {
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

    private static void persistCustomers() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(CUSTOMER_PATH.toFile(), customers);

        } catch (IOException e) {
            throw new CouldNotWriteCustomersException();
        }
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
