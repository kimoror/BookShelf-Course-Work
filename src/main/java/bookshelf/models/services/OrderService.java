package bookshelf.models.services;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.OrderDto;
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
     * @param orderDto dto to save
     */
    public void save(OrderDto orderDto){

        orderRepo.save(DtoConverter.dtoToOrder(orderDto));
    }

    /**
     * Find all orders
     *
     * @return orders
     */
    public List<OrderDto> findAll(){
        return DtoConverter.orderListToDtos(orderRepo.findAll());
    }
}