package service;

import exceptions.*;
import model.Admin;
import org.junit.Assert;
import org.junit.Test;

public class AdminServiceTest {

    private AdminService adminService = new AdminService();

    private static final String EXPECTED_ENCODED_PASSWORD = "��34<lBA��\u001D|���Qa\u0005Y`�s,|=v?�d�\u007FR ��4ȖݢC�w}\u000F�\u0013�k�\u0019��D�ɓ���";
    private static final String SALT = "ABC";
    private static final String PW = "123";

    @Test(expected = EmptyUsernameException.class)
    public void testCheckUserNameIsNotEmpty_empty() throws EmptyUsernameException {
        AdminService.checkUsernameIsNotEmpty("");
    }

    @Test()
    public void testCheckUserNameIsNotEmpty_nonEmpty() throws EmptyUsernameException {
        AdminService.checkUsernameIsNotEmpty("MOCK_USERNAME");
    }

    @Test
    public void testCheckAccount_exists() throws EmptyUsernameException, EmptyIDException, EmptyPasswordException, UsernameAlreadyExistsException, WrongUsernameException, WrongPasswordException, WrongIDException {
        adminService.addAdmin("Boogie", "123", "123");

        adminService.checkAdmin("Boogie", "123", "123");
    }
}
