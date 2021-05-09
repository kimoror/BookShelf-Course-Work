package bookshelf.context;

import bookshelf.exceptions.OrderNotFoundException;
import bookshelf.models.entities.Order;
import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.entities.User;
import bookshelf.models.services.OrderService;
import bookshelf.models.services.ProductInOrderService;
import bookshelf.models.services.ProductService;
import bookshelf.models.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderContext {

    final OrderService orderService;
    final UserService userService;
    private User user;
    private Order order;
    private final ProductInOrderService productInOrderService;
    private final ProductService productService;

    public OrderContext(OrderService orderService, UserService userService,ProductService productService,ProductInOrderService productInOrderService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.productInOrderService = productInOrderService;

    }

    public void setUser(){
        this.user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

//    public Boolean isOrderExistByUserId(){
//        return orderService.findActiveOrdersByUser_id(this.user.getId()) != null;
//    }

    public void addOrder(Long id){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null){
            this.order = new Order();
            this.order.setUser(this.user);
            orderService.save(this.order);
        }
        else{
            productInOrderService.save(
                    new ProductInOrder(this.order.getId(), productService.findById(id).getId(), 1)
            );
        }
    }

    public List<Product> getProductsFromOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
//        return productService.findAllByIdList(
//                productInOrderService.getAllProduct_idByOrderId(this.order.getId()));
        return productInOrderService.getProductsByOrder(this.order.getId());
    }
}
