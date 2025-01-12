package bookShelf.exception.userException;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail(String message)  {
        super(message);
    }
}
