package giuliasilvestrini.GestioneDispositivi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@ToString

public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String avatarURL;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<Device> devices;


}

