package bookshelf.models.repository;

import bookshelf.models.entities.Order;
import bookshelf.models.entities.Product;
import bookshelf.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    public Optional<Order> findOrderByUser_Id(long user_id);

//    @Query(value = "")
//    public Boolean existsByUserIdAndOrderStatusIsRequire(long user_id);

    @Query(value = "SELECT o from Order o where o.orderStatus = 'ACTIVE' AND o.user.id = :user_id")
    public Order findActiveOrdersByUser_id(long user_id);
}
