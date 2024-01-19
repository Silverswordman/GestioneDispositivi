package giuliasilvestrini.GestioneDispositivi.payloads;

import giuliasilvestrini.GestioneDispositivi.entities.enums.DeviceType;
import lombok.Getter;

import java.util.UUID;

@Getter

public class NewDevice {

    private UUID id;
    private DeviceType deviceType;
}
