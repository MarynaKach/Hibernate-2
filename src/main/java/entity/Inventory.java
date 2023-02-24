package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
