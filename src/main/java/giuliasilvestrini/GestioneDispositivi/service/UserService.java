package giuliasilvestrini.GestioneDispositivi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.exceptions.BadRequestException;
import giuliasilvestrini.GestioneDispositivi.exceptions.NotFoundException;
import giuliasilvestrini.GestioneDispositivi.payloads.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import giuliasilvestrini.GestioneDispositivi.repositories.UserDAO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;


    public Page<User> getUsers(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return userDAO.findAll(pageable);
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public User save(NewUserDTO body) {
        // Verifico se l'email è già in uso
		/*Optional<User> user = usersDAO.findByEmail(body.getEmail());
		if(user.isPresent()) throw new RuntimeException();*/

        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setUsername(body.username());
        newUser.setPassword(body.password());
        return userDAO.save(newUser);
    }

    public User findByIdAndUpdate(UUID id, NewUserDTO body) {
        User found = this.findById(id);
        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());
        found.setPassword(body.password());


        return userDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        User userFound = this.findById(id);
        userDAO.delete(userFound);
    }

    public String uploadProfile(MultipartFile file) throws IOException {
        String url = (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }

    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }

}
