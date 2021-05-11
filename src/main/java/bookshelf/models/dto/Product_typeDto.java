package bookshelf.models.dto;

import bookshelf.models.entities.Product_type;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product_typeDto {
    private long id;
    private String name;

    public Product_typeDto(Product_type product_type){
        this.id = product_type.getId();
        this.name = product_type.getName();
    }
}
