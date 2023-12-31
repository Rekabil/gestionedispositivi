package bilgenkaanremzi.gestionedispositivi.controllers;

import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.exceptions.BadRequestException;
import bilgenkaanremzi.gestionedispositivi.payloads.NewUserDTO;
import bilgenkaanremzi.gestionedispositivi.payloads.UserLoginDTO;
import bilgenkaanremzi.gestionedispositivi.payloads.UserLoginSuccessDTO;
import bilgenkaanremzi.gestionedispositivi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    public User register(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return authService.save(body);
        }
    }



}
