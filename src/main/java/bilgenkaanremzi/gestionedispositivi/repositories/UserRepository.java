package bilgenkaanremzi.gestionedispositivi.repositories;

import bilgenkaanremzi.gestionedispositivi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
