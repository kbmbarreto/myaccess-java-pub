package br.com.lambdateam.myaccessjava.controller;

import br.com.lambdateam.myaccessjava.dtos.UserDto;
import br.com.lambdateam.myaccessjava.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@Tag(name = "Endpoint de usuários", description = "Manipula dados de usuários.")
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;
    @GetMapping
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public Iterable<UserDto> getUsers() {

        return service.findAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return service.findUserById(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@Valid @RequestBody UserDto
                                    userDto)
            throws NoSuchAlgorithmException {
        return service.createUser(userDto,
                userDto.getPassword());
    }

    @PatchMapping(value = "/{id}")
    public void patchUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {

        service.updateUser(id, userDto, userDto.getPassword());
    }

    @PutMapping(value = "/{id}")
    public void putUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {

        service.updateUser(id, userDto, userDto.getPassword());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") Long id) {
        service.removeUserById(id);
    }
}