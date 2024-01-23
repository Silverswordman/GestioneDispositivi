package giuliasilvestrini.GestioneDispositivi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;


public record NewUserDTO(

        @NotEmpty(message = "Username cannot be empty, Lo username è un campo obbligatorio")
        @Size(min = 2, max = 20)
        String username,

        @NotEmpty(message = "Name cannot be empty, Il nome è un campo obbligatorio")
        @NotBlank
        String name,

        @NotEmpty(message = "Surname cannot be empty, Il cognome è un campo obbligatorio")
        @NotBlank
        String surname,

        @NotEmpty
        @NotBlank
        @Email(message = "")
        String email,

        @NotEmpty(message = "Password cannot be empty,La password è un campo obbligatorio!")
        @NotBlank
        @Size(min = 5, message = "La password deve avere minimo 5 caratteri!")
        String password) {

}

