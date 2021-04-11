package bookshelf.models.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table( name = "makers")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Maker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
