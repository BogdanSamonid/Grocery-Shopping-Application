package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Admin;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminService {

    private List<Admin> admins = new ArrayList<Admin>();

    public void loadAdminsFromFile() { /*LOAD THE LIST WITH JSON(ADMIN) OBJECTS*/
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("C:/Users/Oana Tomuta/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/admin.json"));
            TypeReference<List<Admin>> typeReference = new TypeReference<List<Admin>>() {
            };
            admins = mapper.readValue(inputStream, typeReference);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAdmin(String username, String ID, String password) throws UsernameAlreadyExistsException, EmptyPasswordException, EmptyUsernameException, EmptyIDException {
        loadAdminsFromFile();
        checkAdminDoesNotExist(username);
        checkUsernameIsNotEmpty(username);
        checkIDIsNotEmpty(ID);
        checkPasswordIsNotEmpty(password);
        Admin newAdmin = new Admin(username, ID, encodePassword(username, password));
        admins.add(newAdmin);
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file=new File("C:/Users/Oana Tomuta/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/admin.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, admins);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void checkAdmin(String username, String ID, String password) throws EmptyPasswordException, EmptyUsernameException, EmptyIDException, WrongPasswordException, WrongIDException, WrongUsernameException {
            loadAdminsFromFile();
            checkUsernameIsNotEmpty(username);
            checkIDIsNotEmpty(ID);
            checkPasswordIsNotEmpty(password);
            checkAccount(username, ID, password);
    }

    private void checkAccount(String username, String ID, String password) throws WrongPasswordException, WrongIDException, WrongUsernameException {
        int ok = 0;
        for (Admin admin : admins) {
            if(Objects.equals(username, admin.getUsername()))
            {
                ok=1;
                if(Objects.equals(ID, admin.getID()))
                {
                    ok = 2;
                    if (Objects.equals(encodePassword(username, password), admin.getPassword()))
                        ok = 3;
                }
            }
        }
        if(ok==0)
            throw new WrongUsernameException();
        if(ok==1)
            throw new WrongIDException();
        if(ok==2)
            throw new WrongPasswordException();
    }

    private void checkAdminDoesNotExist(String username) throws UsernameAlreadyExistsException, NullPointerException{

        for (Admin admin : admins) {
            if (Objects.equals(username, admin.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    static void checkUsernameIsNotEmpty(String username)throws EmptyUsernameException {

        if(Objects.equals(username, ""))
            throw new EmptyUsernameException(username);
    }

    private void checkIDIsNotEmpty(String ID)throws EmptyIDException {

        if(Objects.equals(ID, ""))
            throw new EmptyIDException(ID);
    }

    private void checkPasswordIsNotEmpty(String password)throws EmptyPasswordException {

        if(Objects.equals(password,""))
            throw new EmptyPasswordException(password);
    }


    String encodePassword(String salt, String password) {

        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private MessageDigest getMessageDigest() {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-512");

        } catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException("SHA-512 does not exist!");
        }

        return md;
    }
}
