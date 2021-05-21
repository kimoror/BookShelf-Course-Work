package bookshelf.models.dto;

import bookshelf.models.entities.ProductInOrder;
import lombok.*;

/**
 * Dto of {@link ProductInOrder}
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInOrderDto {
    private long id;
    private long order_id;
    private long product_id;
    private int num_of_product;

    public ProductInOrderDto(ProductInOrder productInOrder){
        this.order_id = productInOrder.getOrder_id();
        this.product_id = productInOrder.getOrder_id();
        this.num_of_product = productInOrder.getNum_of_product();
    }
}
