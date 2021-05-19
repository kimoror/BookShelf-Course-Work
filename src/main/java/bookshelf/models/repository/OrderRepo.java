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

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    public Optional<Order> findOrderByUser_Id(long user_id);

//    @Query(value = "")
//    public Boolean existsByUserIdAndOrderStatusIsRequire(long user_id);

    @Query(value = "SELECT o from Order o where o.orderStatus = 'ACTIVE' AND o.user.id = :user_id")
    public Order findActiveOrdersByUser_id(long user_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET orderStatus = 'CLOSE' WHERE id = :order_id")
    public void makeOrderStatusClosed(long order_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET order_time = :orderTime WHERE id = :order_id")
    public void setOrderTime(long order_id, Date orderTime);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET orderStatus = 'CANCELED' WHERE id = :order_id")
    public void makeOrderStatusCanceled(long order_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Order SET orderStatus = :status WHERE id = :order_id")
    public void changeStatus(long order_id, OrderStatus status);
}
