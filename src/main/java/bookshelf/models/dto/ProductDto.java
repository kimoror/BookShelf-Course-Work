package bookshelf.models.dto;

import bookshelf.models.entities.Product;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private int id;
    private String name;
    private int cost;
    private String img_path;
    private String description;
    private String short_description;
    private MakerDto maker;
    private Product_typeDto product_type;

    public ProductDto(Product product){
        this.name = product.getName();
        this.cost = product.getCost();
        this.img_path = product.getImg_path();
        this.description = product.getDescription();
        this.maker = DtoConverter.makerToDto(product.getMaker());
        this.product_type = DtoConverter.product_typeToDto(product.getProduct_type());
    }
}
