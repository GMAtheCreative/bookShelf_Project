package bookShelf.exception.bookException;

public class MediaStorageException extends RuntimeException {
    public MediaStorageException(String message){
      super(message);
    }
    public MediaStorageException(String message, Throwable cause) {
      super(message, cause);
    }
}
