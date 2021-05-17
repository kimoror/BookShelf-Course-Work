package bookshelf.context;

import bookshelf.exceptions.OrderNotFoundException;
import bookshelf.models.entities.*;
import bookshelf.models.services.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Map;


@Component
public class OrderContext {

    final OrderService orderService;
    final UserService userService;
    private User user;
    private Order order;
    private final ProductInOrderService productInOrderService;
    private final ProductService productService;
    private final EmailService emailService;

    public OrderContext(OrderService orderService,
                        UserService userService,
                        ProductService productService,
                        ProductInOrderService productInOrderService,
                        EmailService emailService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.productInOrderService = productInOrderService;
        this.emailService = emailService;
    }

    public void setUser(){
        this.user = userService
                .findByEmail(
                        SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
    }

    public User getUser() {
        return user;
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

    public Map<Product, Integer> getProductsFromOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        return productInOrderService.getProductsByOrder(this.order.getId());
    }

    public void deleteProductFromOrder(long product_id){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        productInOrderService.removeProductFromOrder(this.order.getId(), product_id);
    }

    public void numOfProductDesc(long product_id){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        productInOrderService.numOfProductDesc(this.order.getId(), product_id);
    }

    public void numOfProductAsc(long product_id){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        productInOrderService.numOfProductAsc(this.order.getId(), product_id);
    }

    public String createMessageBuy(){
        Map<Product, Integer> productNumMap = productInOrderService.getProductsByOrder(this.order.getId());

        StringBuilder message = new StringBuilder();
        message.append("Thank you for buying in our shop!\n")
                .append("Your order:\n")
                .append("Order number: ").append(this.order.getId())
                .append(". Order time: ").append(this.order.getOrder_time()).append(".\n")
                .append("Products:\n");
        productNumMap.forEach((key, value) -> {
            message.append(key.getName())
                    .append(" cost: ").append(key.getCost())
                    .append(". Maker: ").append(key.getMaker().getName())
                    .append(". Type: ").append(key.getProduct_type().getName());
            message.append("\nNumber of products: ").append(value)
                    .append("\n-----------------------\n");
        });
        return message.toString();
    }

    @Transactional
    public void buyOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();

        Date currentDate = new Date(System.currentTimeMillis());
        orderService.buyProduct(this.order.getId(), currentDate);
        emailService.sendMessageBuy(createMessageBuy(), this.user.getEmail());
    }

    public void cancelOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        orderService.makeOrderStatusCanceled(this.order.getId());
        productInOrderService.deleteProductsFromCanceledOrder(this.order.getId());
    }
}
