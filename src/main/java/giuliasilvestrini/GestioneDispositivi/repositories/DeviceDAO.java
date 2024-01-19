package giuliasilvestrini.GestioneDispositivi.repositories;

import giuliasilvestrini.GestioneDispositivi.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceDAO extends JpaRepository<Device, UUID> {
}
