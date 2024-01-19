package giuliasilvestrini.GestioneDispositivi.payloads;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewUserResponse {
    private UUID id;

    private String username;

    private String email;

}
