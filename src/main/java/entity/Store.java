package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "store", schema = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte storeId;
    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
