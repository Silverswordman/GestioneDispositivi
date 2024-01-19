package giuliasilvestrini.GestioneDispositivi.entities;

import giuliasilvestrini.GestioneDispositivi.entities.enums.DeviceState;
import giuliasilvestrini.GestioneDispositivi.entities.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Entity
@ToString

public class Device {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Enumerated(EnumType.STRING)
    private DeviceState deviceState;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
