package models;

import br.com.lambdateam.myaccessjava.models.PasswordModel;
import br.com.lambdateam.myaccessjava.models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordModelTest {

    private PasswordModel passwordModel;
    private UserModel userModel;

    @BeforeEach
    public void setUp() {
        userModel = new UserModel("username", "email@example.com");
        passwordModel = PasswordModel.builder()
                .description("Description")
                .url("http://example.com")
                .username("username")
                .password("password")
                .notes("Notes")
                .userId(userModel)
                .build();
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String description = "New Description";
        String url = "https://example.com";
        String username = "new_username";
        String password = "new_password";
        String notes = "New Notes";
        UserModel newUserModel = new UserModel("new_user", "new_email@example.com");

        passwordModel.setId(id);
        passwordModel.setDescription(description);
        passwordModel.setUrl(url);
        passwordModel.setUsername(username);
        passwordModel.setPassword(password);
        passwordModel.setNotes(notes);
        passwordModel.setUserId(newUserModel);

        Assertions.assertEquals(id, passwordModel.getId());
        Assertions.assertEquals(description, passwordModel.getDescription());
        Assertions.assertEquals(url, passwordModel.getUrl());
        Assertions.assertEquals(username, passwordModel.getUsername());
        Assertions.assertEquals(password, passwordModel.getPassword());
        Assertions.assertEquals(notes, passwordModel.getNotes());
        Assertions.assertEquals(newUserModel, passwordModel.getUserId());
    }
}
