package bookshelf.models.dto;

import bookshelf.models.entities.ProductInOrder;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInOrderDto {
    private long id;
    private OrderDto order;
    private ProductDto product;
    private int num_of_product;

    public ProductInOrderDto(ProductInOrder productInOrder){
        this.order = DtoConverter.orderToDto(productInOrder.getOrder());
        this.product = DtoConverter.productToDto(productInOrder.getProduct());
        this.num_of_product = productInOrder.getNum_of_product();
    }
}
