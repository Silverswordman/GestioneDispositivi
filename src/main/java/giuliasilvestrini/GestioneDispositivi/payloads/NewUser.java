package giuliasilvestrini.GestioneDispositivi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NewUser {

    @NotEmpty(message = "Username cannot be empty, Lo username è un campo obbligatorio")
    @Size(min = 2, max = 20)
    private String username;

    @NotEmpty(message = "Name cannot be empty, Il nome è un campo obbligatorio")
    @NotBlank
    private String name;

    @NotEmpty(message = "Surname cannot be empty, Il cognome è un campo obbligatorio")
    @NotBlank
    private String surname;

    @NotEmpty
    @NotBlank
    @Email(message = "")
    private String email;

    @NotEmpty(message = "Password cannot be empty,La password è un campo obbligatorio!")
    @NotBlank
    @Size(min = 5, message = "La password deve avere minimo 5 caratteri!")
    private String password;

}