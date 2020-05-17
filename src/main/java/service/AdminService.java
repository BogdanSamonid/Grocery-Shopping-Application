package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Admin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AdminService {

    private static List<Admin> admins = new ArrayList<Admin>();

    public static void loadAdminsFromFile() { /*LOAD THE LIST WITH JSON(ADMIN) OBJECTS*/
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("C:/Users/User/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/admin.json"));
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

    public static void addAdmin(String username, String ID, String password) throws UsernameAlreadyExistsException, EmptyPasswordException, EmptyUsernameException, EmptyIDException {
        loadAdminsFromFile();
        checkAdminDoesNotExist(username);
        checkUsernameIsNotEmpty(username);
        checkIDIsNotEmpty(ID);
        checkPasswordIsNotEmpty(password);
        Admin newAdmin = new Admin(username, ID, encodePassword(username, password));
        admins.add(newAdmin);
        try {
            ObjectMapper mapper = new ObjectMapper();
            final String json=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newAdmin);
            Files.write(new File("C:/Users/User/Documents/Grocery-Shopping-Application/src/main/resources/datastorage/admin.json").toPath(), Arrays.asList(json), StandardOpenOption.APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void checkAdmin(String username, String ID, String password) throws EmptyPasswordException, EmptyUsernameException, EmptyIDException, WrongPasswordException, WrongIDException, WrongUsernameException {
            loadAdminsFromFile();
            checkUsernameIsNotEmpty(username);
            checkIDIsNotEmpty(ID);
            checkPasswordIsNotEmpty(password);
            checkAccount(username, ID, password);
    }

    private static void checkAccount(String username, String ID, String password) throws WrongPasswordException, WrongIDException, WrongUsernameException {
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

    private static void checkAdminDoesNotExist(String username) throws UsernameAlreadyExistsException, NullPointerException{

        for (Admin admin : admins) {
            if (Objects.equals(username, admin.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkUsernameIsNotEmpty(String username)throws EmptyUsernameException {

        if(Objects.equals(username, ""))
            throw new EmptyUsernameException(username);
    }

    private static void checkIDIsNotEmpty(String ID)throws EmptyIDException {

        if(Objects.equals(ID, ""))
            throw new EmptyIDException(ID);
    }

    private static void checkPasswordIsNotEmpty(String password)throws EmptyPasswordException {

        if(Objects.equals(password,""))
            throw new EmptyPasswordException(password);
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
