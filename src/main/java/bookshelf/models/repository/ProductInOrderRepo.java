package bookshelf.models.repository;

import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ProductInOrderRepo extends JpaRepository<ProductInOrder, Long> {
    @Query(value = "SELECT p from ProductInOrder pio left join Product p on p.id = pio.product_id where :order_id = pio.order_id")
    public List<Product> getProductsByOrderId(long order_id);

    @Query(value = "SELECT pio from ProductInOrder pio WHERE pio.order_id = :order_id AND pio.product_id = :product_id")
    public List<ProductInOrder> findAllByOrder_idAndProduct_Id(long order_id, long product_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ProductInOrder SET num_of_product = num_of_product + 1 WHERE order_id = :order_id AND product_id = :product_id")
    public void numOfProductInc(long order_id, long product_id);
}
