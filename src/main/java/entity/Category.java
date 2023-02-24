package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    private String name;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films;
}
