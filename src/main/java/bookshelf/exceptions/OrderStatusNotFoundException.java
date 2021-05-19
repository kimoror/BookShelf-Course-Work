package bookshelf.exceptions;

public class OrderStatusNotFoundException extends RuntimeException{
    public OrderStatusNotFoundException() {
        super("Order status doesn't exist");
    }
}
