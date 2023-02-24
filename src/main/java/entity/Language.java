package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "language", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class Language {
    @Id
    @Column(name = "language_id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;
    @Column(columnDefinition = "char")
    private String name;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
