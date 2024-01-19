package giuliasilvestrini.GestioneDispositivi.controller;

import giuliasilvestrini.GestioneDispositivi.entities.Device;
import giuliasilvestrini.GestioneDispositivi.payloads.NewDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import giuliasilvestrini.GestioneDispositivi.service.DeviceService;

import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    // get generale dei device
    @GetMapping("")
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size) {
        return deviceService.getDevices(page, size);
    }

    @GetMapping("/{id}")
    public Device getDevicesById(@PathVariable UUID id) {
        return deviceService.findById(id);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody NewDevice body) {
        return deviceService.save(body);
    }


    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable UUID id, @RequestBody  NewDevice body) {
        return deviceService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable UUID id) {
        deviceService.findByIdAndDelete(id);
    }
}