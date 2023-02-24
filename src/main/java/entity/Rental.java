package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Rental {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;
    @Column(name = "rental_date")
    private LocalDateTime rentalDate;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
