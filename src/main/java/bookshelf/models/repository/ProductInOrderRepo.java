package bookshelf.models.repository;

import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.entities.Product_type;
import org.eclipse.core.internal.boot.PlatformURLBaseConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Repository for {@link ProductInOrder}
 */
@Repository
public interface ProductInOrderRepo extends JpaRepository<ProductInOrder, Long> {

    /**
     * @param order_id id of the current order
     * @param product_id - id of the product in current order whom we want to find
     * @return list of product with current order by order_id and product_id
     */
    @Query(value = "SELECT pio from ProductInOrder pio WHERE pio.order_id = :order_id AND pio.product_id = :product_id")
    public List<ProductInOrder> findAllByOrder_idAndProduct_Id(long order_id, long product_id);

    /**
     * Increment quantity of the product
     * @param order_id id of current order
     * @param product_id - id of the product in the current order the quantity which we want to increment
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE ProductInOrder SET num_of_product = num_of_product + 1 WHERE order_id = :order_id AND product_id = :product_id")
    public void numOfProductInc(long order_id, long product_id);

    /**
     * @param order_id - id of the order whose products we want to find
     * @return list of products that are in this order
     */
    @Query(value = "SELECT p FROM ProductInOrder pio LEFT JOIN Product p ON p.id = pio.product_id WHERE pio.order_id = :order_id")
    public List<Product> getProductsByOrder(long order_id);

    /**
     * @param order_id - id of the current order
     * @param product_id - id of the product in the current order the quantity which we want to find
     * @return quantity of product by order id and product id
     */
    @Query(value = "SELECT pio.num_of_product FROM ProductInOrder pio WHERE pio.order_id = :order_id AND pio.product_id = :product_id")
    public Integer getNumOfProductsByOrder_idAAndProduct_id(long order_id, long product_id);

    /**
     * @param order_id - id of the current order
     * @param product_id - id of the product in the current order whom we want to remove
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ProductInOrder pio WHERE pio.order_id = :order_id AND pio.product_id = :product_id")
    public void removeByOrder_idAndProduct_id(long order_id, long product_id);

    /**
     * Increment quantity of the product
     * @param order_id id of current order
     * @param product_id - id of the product in the current order the quantity which we want to decrement
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE ProductInOrder SET num_of_product = num_of_product - 1 WHERE order_id = :order_id AND product_id = :product_id")
    public void numOfProductDesc(long order_id, long product_id);


    /**
     * After the user has canceled the order, we clear the extra products from the ProductInOrder table
     * @param order_id - id of current order
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ProductInOrder pio WHERE pio.order_id = :order_id")
    public void deleteProductsFromCanceledOrder(long order_id);
}
