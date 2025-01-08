package bookShelf.exception.bookException;

public class BookNotFound extends RuntimeException {
    public BookNotFound(String message) {
        super(message);
    }
}
