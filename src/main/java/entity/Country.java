package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "country", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short countryId;

    private String country;
    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
