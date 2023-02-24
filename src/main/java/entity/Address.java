package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "address", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String address;

    private String address2;

    private String district;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Column(name = "postal_code")
    private String postalCode;

    private String phone;
    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lasUpdate;
}
