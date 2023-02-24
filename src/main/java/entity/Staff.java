package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Staff {
    @Id
    @Column(name = "staff_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte staffId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] picture;
    private String email;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;
    private String username;
    private String password;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
