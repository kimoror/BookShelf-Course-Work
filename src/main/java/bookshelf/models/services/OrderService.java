package bookshelf.models.services;

import bookshelf.models.entities.Order;
import bookshelf.models.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     *
     * @param order entity to save
     */
    public void save(Order order){
        orderRepo.save(order);
    }

    /**
     * Find all orders
     *
     * @return orders
     */
    public List<Order> findAll(){
        return orderRepo.findAll();
    }
}
