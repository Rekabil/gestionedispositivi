package bilgenkaanremzi.gestionedispositivi.entities;

import bilgenkaanremzi.gestionedispositivi.enums.Availability;
import bilgenkaanremzi.gestionedispositivi.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Optional;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
private DeviceType deviceType;
    @Enumerated(EnumType.STRING)
private Availability availability;

    @CreationTimestamp
    private Date createdAt;

@ManyToOne
@JoinColumn(name = "userId")
    private User user;

}
