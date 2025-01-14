package bookShelf.utils.user;

import bookShelf.exception.userException.InvalidEmail;
import bookShelf.exception.userException.InvalidPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class RegexValidation {
    private static final String EMAIL= "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";;
    private static final String PASSWORD= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

    private static PasswordEncoder passwordEncoder;

    public static void validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL);
        if (!pattern.matcher(email).matches()) {
            throw new InvalidEmail("Invalid Email");
        }
    }

    public static void validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD);
        if (!pattern.matcher(password).matches()) {
            throw new InvalidPassword("Password must be between 6-20 characters..." +
                    " Password must contain at least one symbol, one lower-case, " +
                    "one upper-case, and one numeric value.");
        }
    }

    public static String hashedValue (String value){
        return passwordEncoder.encode(value);
    }
}
