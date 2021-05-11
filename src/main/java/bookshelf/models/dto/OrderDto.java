package bookshelf.models.dto;

import bookshelf.models.entities.Order;
import bookshelf.models.enums.OrderStatus;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private long id;
    private UserDto user;
    private Date order_time;
    private OrderStatus orderStatus;

    public OrderDto(Order order){
        this.id = order.getId();
        this.order_time = order.getOrder_time();
        this.user = DtoConverter.userToDto(order.getUser());
        this.orderStatus = order.getOrderStatus();
    }
}