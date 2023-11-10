package bilgenkaanremzi.gestionedispositivi.controllers;

import bilgenkaanremzi.gestionedispositivi.entities.Device;
import bilgenkaanremzi.gestionedispositivi.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceControllers {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("")
    public Page<Device> getDevice(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "20") int size,@RequestParam(defaultValue = "id") String orderby){
        return deviceService.GetUsers(page, size, orderby);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody Device body) {return deviceService.save(body);}

    @GetMapping("/{id}")
    public Device getById(@PathVariable int id) {
        return deviceService.findById(id);
    }
    @PutMapping("/{id}")
    public Device findAndUpdate(@PathVariable int id,@RequestBody Device body) {
        return  deviceService.findAndUpdate(id,body);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int id){ deviceService.findAndDelete(id);}


}
