package bookshelf.exceptions;

public class EnumStatusNotFoundException extends RuntimeException{
    public EnumStatusNotFoundException(String string) {
        super(string);
    }
}
