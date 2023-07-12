package models;

import br.com.lambdateam.myaccessjava.models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserModelTest {

    private UserModel userModel;

    @BeforeEach
    public void setUp() {
        userModel = new UserModel("username", "email@example.com");
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        byte[] storedHash = {1, 2, 3};
        byte[] storedSalt = {4, 5, 6};

        userModel.setId(id);
        userModel.setStoredHash(storedHash);
        userModel.setStoredSalt(storedSalt);

        Assertions.assertEquals(id, userModel.getId());
        Assertions.assertArrayEquals(storedHash, userModel.getStoredHash());
        Assertions.assertArrayEquals(storedSalt, userModel.getStoredSalt());
    }

    @Test
    public void testConstructorWithUsernameAndEmail() {
        String username = "username";
        String email = "email@example.com";

        Assertions.assertEquals(username, userModel.getUser());
        Assertions.assertEquals(email, userModel.getEmail());
    }
}