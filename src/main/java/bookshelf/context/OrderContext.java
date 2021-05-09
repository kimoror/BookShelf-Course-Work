package bookshelf.context;

import bookshelf.models.entities.Order;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.entities.User;
import bookshelf.models.services.OrderService;
import bookshelf.models.services.ProductInOrderService;
import bookshelf.models.services.ProductService;
import bookshelf.models.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class OrderContext {

    final OrderService orderService;
    final UserService userService;
    private User user;
    private Order order;
    private ProductInOrderService productInOrderService;
    private ProductService productService;

    public OrderContext(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public void setUser(){
        this.user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Boolean isOrderExistByUserId(){
        return orderService.existsByUserId(user.getId());
    }

    public void addOrder(Long id){
        if(isOrderExistByUserId()){
            this.order = new Order();
            order.setUser(user);
            orderService.save(order);
        }
        else{
//            productInOrderService.save(new ProductInOrder(order, productService.findById(id)))
        }
    }


}
