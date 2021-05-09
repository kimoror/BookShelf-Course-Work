package bookshelf.models.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products_in_order")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long order_id;
    private long product_id;
    private int num_of_product;
}
