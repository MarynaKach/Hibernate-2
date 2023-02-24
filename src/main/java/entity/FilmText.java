package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;

@Entity
@Table(name = "film_text", schema = "movie")
@NoArgsConstructor
@Getter
@Setter
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short filmId;

    private String title;
    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
