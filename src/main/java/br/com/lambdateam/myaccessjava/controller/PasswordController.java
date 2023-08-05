package br.com.lambdateam.myaccessjava.controller;

import br.com.lambdateam.myaccessjava.dtos.PasswordDto;
import br.com.lambdateam.myaccessjava.exceptions.ExpiredJwtException;
import br.com.lambdateam.myaccessjava.models.PasswordModel;
import br.com.lambdateam.myaccessjava.services.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/password")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Endpoint de senhas", description = "Manipula dados de senhas.")
public class PasswordController {

    private final PasswordService service;
    private final ModelMapper mapper;

    private PasswordDto convertToDto(PasswordModel model) {
        return mapper.map(model, PasswordDto.class);
    }

    private PasswordModel convertToModel(PasswordDto dto) {
        return mapper.map(dto, PasswordModel.class);
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "Retorna todas as senhas cadastradas conforme o usário logado.", responses = {
            @ApiResponse(responseCode = "200", description = "Cadastros descriptografados e retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public ResponseEntity<List<PasswordModel>> listPasswordsByUserId(@PathVariable Long userId) {
        try{
            List<PasswordModel> passwords = service.listAllPasswordsByUserId(userId);
            return ResponseEntity.ok(passwords);
        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @GetMapping("/user/{userId}/description/{description}")
    @Operation(description = "Retorna a busca das senhas cadastradas conforme a descrição e usário logado.", responses = {
            @ApiResponse(responseCode = "200", description = "Cadastros descriptografados e retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public ResponseEntity<List<PasswordModel>> findPasswordsByDescriptionAndUserId(@PathVariable Long userId, @PathVariable String description) throws Exception {
        try{
            List<PasswordModel> passwords = service.findByDescriptionAndUserId(description, userId);
            return ResponseEntity.ok(passwords);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna a busca das senhas cadastradas conforme o id do registro.", responses = {
            @ApiResponse(responseCode = "200", description = "Cadastros descriptografados e retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public ResponseEntity<List<PasswordModel>> findPasswordById(@PathVariable Long id) throws Exception {
        try{
            List<PasswordModel> passwords = service.findPasswordById(id);
            return ResponseEntity.ok(passwords);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @PostMapping
    @Operation(description = "Cria um novo registro de senha.", responses = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado e criptografado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public ResponseEntity<PasswordDto> postPassword(@Valid @RequestBody PasswordDto passwordDto) throws Exception {
        try{
            var model = convertToModel(passwordDto);
            var password = service.createPassword(model);

            return new ResponseEntity(convertToDto(password), HttpStatus.CREATED);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @PutMapping(value = "/{id}")
    @Operation(description = "Atualiza um cadastro conforme o id do registro.", responses = {
            @ApiResponse(responseCode = "201", description = "Cadastro atualizado e criptografado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public void putPassword(@PathVariable("id") Long id, @Valid @RequestBody PasswordDto passwordDto) throws Exception {
        try{
            if(!id.equals(passwordDto.getId())) throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "id does not match."
            );

            var passwordModel = convertToModel(passwordDto);
            service.updatePassword(id, passwordModel);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @PatchMapping(value = "/{id}")
    @Operation(description = "Atualiza um registro de senha conforme o id do registro.", responses = {
            @ApiResponse(responseCode = "201", description = "Cadastros descriptografados e retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public void patchPassword(@PathVariable("id") Long id, @Valid @RequestBody PasswordDto passwordDto) throws Exception {
        try{
            if(!id.equals(passwordDto.getId())) throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "id does not match."
            );

            var passwordModel = convertToModel(passwordDto);
            service.updatePassword(id, passwordModel);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Deleta um registro de senha cadastrado conforme o id do registro.", responses = {
            @ApiResponse(responseCode = "200", description = "Cadastro deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na requisição."),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado ou token expirado."),
    })
    public ResponseEntity<String> deletePasswordById(@PathVariable("id") Long id) {
        try{
            service.deletePasswordById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Password deleted successfully");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(
                    HttpStatus.UNAUTHORIZED,
                    "Token expired."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}