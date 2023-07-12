package br.com.lambdateam.myaccessjava.dtos;

import br.com.lambdateam.myaccessjava.models.UserModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordDto {

    private Long id;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "URL is required")
    private String url;
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
    private String notes;
    @NotNull(message = "User is required")
    private UserModel userId;
}