package giuliasilvestrini.GestioneDispositivi.controller;

import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.payloads.NewUserDTO;
import giuliasilvestrini.GestioneDispositivi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws Exception {
//        return userService.save(body);
//    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody NewUserDTO body) {
        return userService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.findByIdAndDelete(id);
    }

//// upload non completo
//    @PostMapping ("/{id}/upload")
//    public String uploadProfilePic (@RequestParam ("profile") MultipartFile file, @PathVariable UUID id) throws IOException {
//        return userService.uploadProfile(file);
//
//    }
}
