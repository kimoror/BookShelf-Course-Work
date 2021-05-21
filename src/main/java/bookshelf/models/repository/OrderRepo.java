package bookshelf.models.repository;

import bookshelf.models.entities.Order;
import bookshelf.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

/**
 * Repository for {@link Order}
 */
@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    public Optional<Order> findOrderByUser_Id(long user_id);

    /**
     * @param user_id - id of user which order we want to find
     * @return - orders with stats active ({@link OrderStatus})
     */
    @Query(value = "SELECT o from Order o where o.orderStatus = 'ACTIVE' AND o.user.id = :user_id")
    public Order findActiveOrdersByUser_id(long user_id);

    /**
     * make Order status closed
     * @param order_id - id of order whom we want set order status CLOSE
     * @see OrderStatus
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET orderStatus = 'CLOSE' WHERE id = :order_id")
    public void makeOrderStatusClosed(long order_id);

    /**
     * change time of purchase
     * @param order_id - id of order whom we want set order time
     * @param orderTime - order time whom we want to set
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET order_time = :orderTime WHERE id = :order_id")
    public void setOrderTime(long order_id, Date orderTime);

    /**
     * @param order_id - id of order whom we want set order status CANCELED
     * @see OrderStatus
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET orderStatus = 'CANCELED' WHERE id = :order_id")
    public void makeOrderStatusCanceled(long order_id);

    /**
     * @param order_id id of order whom we want change order status
     * @see OrderStatus
     * @param status - new order status
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET orderStatus = :status WHERE id = :order_id")
    public void changeStatus(long order_id, OrderStatus status);
}
