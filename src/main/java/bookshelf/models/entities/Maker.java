package bookshelf.models.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * represent author or manufacturer of product
 */
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
}
