package giuliasilvestrini.GestioneDispositivi.controller;

import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "surname") String sortBy) {
        return userService.getUsers(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public User getUsersById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User body) throws Exception {
        return userService.save(body);
    }
}
