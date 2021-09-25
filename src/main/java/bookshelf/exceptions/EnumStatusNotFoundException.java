package bookshelf.exceptions;
/**
 * Throws when enumStatus not found
 */
public class EnumStatusNotFoundException extends RuntimeException{
    public EnumStatusNotFoundException(String string) {
        super(string);
    }
}
