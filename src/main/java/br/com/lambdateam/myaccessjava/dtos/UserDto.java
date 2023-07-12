package br.com.lambdateam.myaccessjava.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    private Long id;
    @NotNull(message = "Name is required")
    private String user;
    @NotNull(message = "E-mail is required")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
}