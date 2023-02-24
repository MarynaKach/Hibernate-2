package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "actor", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Actor {
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lustUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films;
}
