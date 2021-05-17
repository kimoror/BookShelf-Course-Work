package bookshelf.models.repository;

import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import org.eclipse.core.internal.boot.PlatformURLBaseConnection;
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

    //TODO Delete
    @Query(value = "SELECT product_id from ProductInOrder where product_id = :order_id")
    public List<Long> getAllProduct_idByOrderId(long order_id);

    @Query(value = "SELECT p FROM ProductInOrder pio LEFT JOIN Product p ON p.id = pio.product_id WHERE pio.order_id = :order_id")
    public List<Product> getProductsByOrder(long order_id);

    @Query(value = "SELECT pio.num_of_product FROM ProductInOrder pio WHERE pio.order_id = :order_id AND pio.product_id = :product_id")
    public Integer getNumOfProductsByOrder_idAAndProduct_id(long order_id, long product_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ProductInOrder pio WHERE pio.order_id = :order_id AND pio.product_id = :product_id")
    public void removeByOrder_idAndProduct_id(long order_id, long product_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ProductInOrder SET num_of_product = num_of_product - 1 WHERE order_id = :order_id AND product_id = :product_id")
    public void numOfProductDesc(long order_id, long product_id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ProductInOrder pio WHERE pio.order_id = :order_id")
    public void deleteProductsFromCanceledOrder(long order_id);
}
