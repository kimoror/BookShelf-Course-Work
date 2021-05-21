package bookshelf.models.services;

import bookshelf.aspect.Loggable;
import bookshelf.models.entities.Order;
import bookshelf.models.enums.OrderStatus;
import bookshelf.models.repository.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

/**
 * Service for managing {@link Order}. Using {@link OrderRepo}
 */
@Service
@Slf4j
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     *
     * @param order - order to save
     */
    @Transactional
    @Loggable
    public void save(Order order){
        orderRepo.save(order);
    }

    /**
     * Find all orders
     *
     * @return orders
     */
    @Transactional
    @Loggable
    public List<Order> findAll(){
        return orderRepo.findAll();
    }

    @Transactional
    @Loggable
    public Order findActiveOrdersByUser_id(long user_id){
        return orderRepo.findActiveOrdersByUser_id(user_id);
    }

    /**
     * Purchase order. Set order time and make order status CLOSE
     * @see OrderStatus
     * @param order_id - id of the order we want to buy
     * @param date - order purchase date
     */
    @Transactional
    @Loggable
    public void buyProduct(long order_id, Date date){
        orderRepo.setOrderTime(order_id, date);
        orderRepo.makeOrderStatusClosed(order_id);
    }

    @Transactional
    @Loggable
    public void makeOrderStatusCanceled(long order_id){
        orderRepo.makeOrderStatusCanceled(order_id);
    }

    @Transactional
    @Loggable
    public void changeStatus(long order_id, OrderStatus status){
        orderRepo.changeStatus(order_id,status);
    }

}