package bookshelf.context;

import bookshelf.exceptions.OrderNotFoundException;
import bookshelf.models.entities.*;
import bookshelf.models.services.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Map;


/**
 * This class contains order context
 */
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

    /**
     * find user buy email and set user to context
     */
    public void setUser(){
        this.user = userService
                .findByEmail(
                        SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
    }

    /**
     * @return user from current context
     */
    public User getUser() {
        return user;
    }

    /**
     * method save product into current order in productInOrder table
     * @param id - if of product, which we want to add to the order
     */
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


    /**
     * @return Map which contain product(key) and it's quantity(value) in current order
     */
    public Map<Product, Integer> getProductsFromOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        return productInOrderService.getProductsByOrder(this.order.getId());
    }

    /**
     * delete product from current context
     * @param product_id - id of the product being deleted
     */
    public void deleteProductFromOrder(long product_id){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        productInOrderService.removeProductFromOrder(this.order.getId(), product_id);
    }

    /**
     * reduces the amount of product by 1
     * @param product_id - id of product which we want to delete
     */
    public void numOfProductDesc(long product_id){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        productInOrderService.numOfProductDesc(this.order.getId(), product_id);
    }

    /**
     * create template of message
     * @return template for a purchase confirmation message
     */
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

    /**
     * Make order status CLOSE and send message to client email about purchase
     */
    @Transactional
    public void buyOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();

        Date currentDate = new Date(System.currentTimeMillis());
        String subject = "Purchase in the BookShelf store";
        emailService.sendMessageBuy(createMessageBuy(), this.user.getEmail(), subject);
        orderService.buyProduct(this.order.getId(), currentDate);
    }

    /**
     * Make order status CANCEL
     */
    public void cancelOrder(){
        this.order = orderService.findActiveOrdersByUser_id(this.user.getId());
        if(this.order == null)
            throw new OrderNotFoundException();
        orderService.makeOrderStatusCanceled(this.order.getId());
        productInOrderService.deleteProductsFromCanceledOrder(this.order.getId());
    }
}
