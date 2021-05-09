package bookshelf.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super("Order doesn't exist");
    }
}
