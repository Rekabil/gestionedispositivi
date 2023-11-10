package bilgenkaanremzi.gestionedispositivi.repositories;

import bilgenkaanremzi.gestionedispositivi.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
