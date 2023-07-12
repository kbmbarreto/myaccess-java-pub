package br.com.lambdateam.myaccessjava.jwt.controller;

import br.com.lambdateam.myaccessjava.jwt.model.AuthenticationRequest;
import br.com.lambdateam.myaccessjava.jwt.model.AuthenticationResponse;
import br.com.lambdateam.myaccessjava.jwt.service.ApplicationUserDetailsService;
import br.com.lambdateam.myaccessjava.jwt.util.JwtUtil;
import br.com.lambdateam.myaccessjava.models.UserModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate")
    @Tag(name = "Endpoint de autenticação", description = "Utilizado para logar e receber o token.")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest req
    ) throws Exception {
        UserModel user;

        try {
            user = userDetailsService.authenticate(req.getEmail(), req.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        var userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        System.out.println(userDetails);
        var jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}