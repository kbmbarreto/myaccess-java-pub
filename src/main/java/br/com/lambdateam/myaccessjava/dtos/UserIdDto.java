package br.com.lambdateam.myaccessjava.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdDto {

    private Long id;
    private String user;
    private String email;
}