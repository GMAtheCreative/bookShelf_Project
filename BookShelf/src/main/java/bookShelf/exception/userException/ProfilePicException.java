package bookShelf.exception.userException;

import java.io.IOException;

public class ProfilePicException extends RuntimeException {
    public ProfilePicException(String message){
        super(message);
    }
    public ProfilePicException(String message, Throwable cause) {
        super(message, cause);
    }
}
