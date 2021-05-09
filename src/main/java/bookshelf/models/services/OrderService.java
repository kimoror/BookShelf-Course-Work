package bookshelf.models.services;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.OrderDto;
import bookshelf.models.entities.Order;
import bookshelf.models.entities.User;
import bookshelf.models.enums.OrderStatus;
import bookshelf.models.repository.OrderRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     *
     * @param order - order to save
     */
    public void save(Order order){

        orderRepo.save(order);
    }

    /**
     * Find all orders
     *
     * @return orders
     */
    public List<OrderDto> findAll(){
        return DtoConverter.orderListToDtos(orderRepo.findAll());
    }

    @Transactional
    public Order findActiveOrdersByUser_id(long user_id){
        return orderRepo.findActiveOrdersByUser_id(user_id);
    }


}