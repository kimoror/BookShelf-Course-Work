package bookshelf.exceptions;

/**
 * Throws when entity not found
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message){
        super(message);
    }
}
