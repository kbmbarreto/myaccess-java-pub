package br.com.lambdateam.myaccessjava.controller;

import br.com.lambdateam.myaccessjava.dtos.UserDto;
import br.com.lambdateam.myaccessjava.dtos.UserIdDto;
import br.com.lambdateam.myaccessjava.exceptions.NotFoundException;
import br.com.lambdateam.myaccessjava.models.UserModel;
import br.com.lambdateam.myaccessjava.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public UserDto getUserById(@PathVariable("id") Long id) {
        return service.findUserById(id);
    }

    @GetMapping("/email/{email}")
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<UserIdDto> findUserByEmail(@PathVariable String email) {
        try {
            UserIdDto userIdDto = service.findUSerByEmail(email);
            return ResponseEntity.ok(userIdDto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@Valid @RequestBody UserDto
                                    userDto)
            throws NoSuchAlgorithmException {
        return service.createUser(userDto,
                userDto.getPassword());
    }

    @PatchMapping(value = "/{id}")
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public void patchUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {

        service.updateUser(id, userDto, userDto.getPassword());
    }

    @PutMapping(value = "/{id}")
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public void putUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {

        service.updateUser(id, userDto, userDto.getPassword());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Retorna todos os usuários cadastrados.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") Long id) {
        service.removeUserById(id);
    }
}