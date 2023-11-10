package bilgenkaanremzi.gestionedispositivi.services;

import bilgenkaanremzi.gestionedispositivi.entities.Device;
import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.exceptions.NotFoundException;
import bilgenkaanremzi.gestionedispositivi.repositories.DeviceRepository;
import bilgenkaanremzi.gestionedispositivi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public Device save(Device body){
        return deviceRepository.save(body);
    }

    public Page<Device> GetUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return deviceRepository.findAll(pageable);
    }

    public Device findById(int id) throws NotFoundException {
        return deviceRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public void findAndDelete(int id) throws NotFoundException{
        Device found = this.findById(id);
        deviceRepository.delete(found);
    }

    public Device findAndUpdate(int id, Device body) {
        Device found = this.findById(id);
        found.setId(id);
        found.setAvailability(body.getAvailability());
        found.setUser(body.getUser());
        return deviceRepository.save(found);
    }
}
