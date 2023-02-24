package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short customerId;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "first_name")
    private String firstName;
    @Column(name= "last_name")
    private String lastName;
    @Column(name = "email", length = 50)
    private String email;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;
    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
