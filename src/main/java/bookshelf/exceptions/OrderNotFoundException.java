package bookshelf.exceptions;

/**
 * Throws when order not found
 */
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super("Order doesn't exist");
    }
}
