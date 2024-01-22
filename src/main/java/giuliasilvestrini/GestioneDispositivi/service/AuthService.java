package giuliasilvestrini.GestioneDispositivi.service;

import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.exceptions.UnathorizedException;
import giuliasilvestrini.GestioneDispositivi.payloads.UserLoginDTO;
import giuliasilvestrini.GestioneDispositivi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        // 1. Verifichiamo che l'email dell'utente sia nel db
        User user = usersService.findByEmail(body.email());

        // 2. In caso affermativo, verifichiamo se la password fornita corrisponde a quella trovata nel db
        if (body.password().equals(user.getPassword())) {
            // 3. Se le credenziali sono OK --> Genere un token JWT e lo ritorno
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401 (Unauthorized)
            throw new UnathorizedException("Credenziali non valide!");
        }
    }
}