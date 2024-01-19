package giuliasilvestrini.GestioneDispositivi.service;

import giuliasilvestrini.GestioneDispositivi.entities.Device;
import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.entities.enums.DeviceState;
import giuliasilvestrini.GestioneDispositivi.exceptions.NotFoundException;
import giuliasilvestrini.GestioneDispositivi.payloads.NewDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import giuliasilvestrini.GestioneDispositivi.repositories.DeviceDAO;

import java.util.Random;
import java.util.UUID;


@Service

public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private UserService userService;


    public Page<Device> getDevices(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return deviceDAO.findAll(pageable);
    }

    public Device save(NewDevice body) {
        Device newDevice = new Device();
        newDevice.setDeviceType(body.getDeviceType());

        // Verifica se l'ID dell'utente è presente nel body? Optional nn andava XD

        if (body.getId() != null) {
            UUID userId = body.getId();
            User user = userService.findById(userId);
            newDevice.setUser(user);

            newDevice.setDeviceState(getRandomDeviceState());
        } else {
            newDevice.setDeviceState(getRandomDeviceState2());
        }

        return deviceDAO.save(newDevice);
    }



    // random per setting degli enum automatico

    private DeviceState getRandomDeviceState() {
        Random random = new Random();
        DeviceState[] usedDevices = {DeviceState.Unavailable, DeviceState.Maintenance};
        int x = random.nextInt(usedDevices.length);
        return usedDevices[x];
    }

    private DeviceState getRandomDeviceState2() {
        Random random = new Random();
        DeviceState[] freeDevices = {DeviceState.Available, DeviceState.Out_Of_Order};
        int x = random.nextInt(freeDevices.length);
        return freeDevices[x];
    }

    public Device findById(UUID id) {
        return deviceDAO.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public void findByIdAndDelete(UUID id) {
        Device deviceFound = this.findById(id);
        deviceDAO.delete(deviceFound);
    }

    public Device findByIdAndUpdate(UUID id, Device body) {

        Device deviceFound = this.findById(id);
        deviceFound.setDeviceType(body.getDeviceType());
        return deviceDAO.save(deviceFound);
    }

}
