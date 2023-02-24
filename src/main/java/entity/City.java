package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "city", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short cityId;

    private String city;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
