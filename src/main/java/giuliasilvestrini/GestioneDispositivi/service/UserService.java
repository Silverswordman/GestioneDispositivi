package giuliasilvestrini.GestioneDispositivi.service;

import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.exceptions.NotFoundException;
import giuliasilvestrini.GestioneDispositivi.payloads.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import giuliasilvestrini.GestioneDispositivi.repositories.UserDAO;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;


    public Page<User> getUsers(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return userDAO.findAll(pageable);
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public User save(NewUser body) {
        return userDAO.save(body);
    }

    public User findByIdAndUpdate(UUID id, NewUser body) {
        User found = this.findById(id);

        found.setUsername(body.getUsername());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());


        return userDAO.save(found);
    }

}
