package bookShelf.utils.user;

import bookShelf.data.models.User;
import bookShelf.dtos.requests.user.RegisterUserRequest;

import static bookShelf.utils.user.RegexValidation.hashedValue;

public class UserMapping {

    public static void setUserDetails(User user, RegisterUserRequest registerUser) {
        String hashedPassword = hashedValue(registerUser.getPassword());
        String hashedEmail = hashedValue(registerUser.getEmail());

        user.setUserName(registerUser.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(hashedEmail);
    }
}
