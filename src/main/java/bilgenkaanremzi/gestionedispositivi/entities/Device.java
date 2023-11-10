package bilgenkaanremzi.gestionedispositivi.entities;

import bilgenkaanremzi.gestionedispositivi.enums.Availability;
import bilgenkaanremzi.gestionedispositivi.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private DeviceType deviceType;
private Availability availability;

    @CreationTimestamp
    private Date createdAt;

@ManyToOne
@JoinColumn(name = "userId")
    private User user;
}
