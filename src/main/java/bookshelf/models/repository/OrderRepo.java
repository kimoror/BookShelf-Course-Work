package bookshelf.models.repository;

import bookshelf.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    public Optional<Order> findOrderByUser_Id(long user_id);

    public Boolean existsByUserId(Long id);
}
