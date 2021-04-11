package bookshelf.models.repository;

import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInOrderRepo extends JpaRepository<ProductInOrder, Long> {
    @Query(value = "SELECT p from ProductInOrder pio left join Product p on p.id = pio.product.id where :order_id = pio.order.id")
    public List<Product> getProductsByOrderId(long order_id);
}
