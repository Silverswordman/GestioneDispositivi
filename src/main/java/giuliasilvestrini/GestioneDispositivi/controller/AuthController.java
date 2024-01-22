package giuliasilvestrini.GestioneDispositivi.controller;

import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.exceptions.BadRequestException;
import giuliasilvestrini.GestioneDispositivi.payloads.NewUser;
import giuliasilvestrini.GestioneDispositivi.payloads.NewUserResponse;
import giuliasilvestrini.GestioneDispositivi.payloads.UserLoginDTO;
import giuliasilvestrini.GestioneDispositivi.payloads.UserLoginResponseDTO;
import giuliasilvestrini.GestioneDispositivi.service.AuthService;
import giuliasilvestrini.GestioneDispositivi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponse createUser(@RequestBody @Validated NewUser newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = userService.save(newUserPayload);

            return new NewUserResponse(newUser.getId());
        }
    }
}