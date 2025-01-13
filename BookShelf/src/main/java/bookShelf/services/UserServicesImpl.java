package bookShelf.services;


import bookShelf.config.SecurityConfiguration;
import bookShelf.data.models.User;
import bookShelf.data.repositories.UserRepository;
import bookShelf.dtos.requests.user.*;
import bookShelf.dtos.responses.user.*;
import bookShelf.exception.bookException.BookNotFound;
import bookShelf.exception.userException.InvalidEmail;
import bookShelf.exception.userException.InvalidPassword;
import bookShelf.exception.userException.ProfilePicException;
import bookShelf.exception.userException.UserNotFound;
import bookShelf.utils.user.RegexValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static bookShelf.utils.user.RegexValidation.validateEmail;
import static bookShelf.utils.user.RegexValidation.validatePassword;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUser) {
        validateEmail(registerUser.getEmail());
        validatePassword(registerUser.getPassword());

        User user = new User();

        String hashedPassword = hashedValue(registerUser.getPassword());
        String hashedEmail = hashedValue(registerUser.getEmail());

        user.setUserName(registerUser.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(hashedEmail);
        userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(registerUser.getUsername() +" Welcome to BookShelf");
        return response;
    }


    @Override
    public LoginUserResponse loginWithUsername(LoginUserRequest loginUser) {
        validatePassword(loginUser.getPassword());
        User existingUser = userRepository.findUserByUserName
                (loginUser.getUsername()).orElseThrow(() -> new UserNotFound
                        (loginUser.getUsername()+ " You need to be registered to login"));

        if(!passwordEncoder.matches(loginUser.getPassword(), existingUser.getPassword())) {
            throw new InvalidPassword("invalid credentials provided");
        }
        LoginUserResponse response = new LoginUserResponse();
        response.setMessage(loginUser.getUsername()+ " Welcome.... Lets get reading");
        response.setId(existingUser.getId());

        return response;
    }

    @Override
    public LoginUserResponse loginWithEmail(LoginUserRequest loginUser) {
        validateEmail(loginUser.getEmail());
        validatePassword(loginUser.getPassword());

        List<User> existingUser =userRepository.findAll();
        User users = existingUser.stream()
                .filter(user -> passwordEncoder.matches(loginUser.getEmail(), user.getEmail()))
                .findFirst()
                .orElseThrow(() -> new UserNotFound(loginUser.getEmail() + " You need to be registered to login"));

        if(!passwordEncoder.matches(loginUser.getPassword(), users.getPassword())) {
            throw new InvalidPassword("invalid credentials provided");
        }

        LoginUserResponse response = new LoginUserResponse();
        response.setMessage(loginUser.getEmail()+ " Welcome.... Lets get reading");
        response.setId(users.getId());
        return response;
    }

    @Override
    public UploadProfilePicResponse profilePic(UploadProfilePicRequest profilePic) {
            User user = userRepository.findById(profilePic.getUserId())
                    .orElseThrow(() -> new UserNotFound("User not found"));

            try {
                byte[] profilePicBytes = profilePic.getPicture().getBytes();
                user.setProfilePicture(profilePicBytes);
                userRepository.save(user);
                UploadProfilePicResponse response = new UploadProfilePicResponse();
                response.setMessage("Profile picture uploaded successfully");
                return response;
            }
            catch (IOException e) {
                throw new ProfilePicException("Failed to upload profile picture", e);
            }

    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePassword) {
        User existingUser = userRepository.findById(changePassword.getUserId())
                .orElseThrow(() -> new UserNotFound("user not found"));
        if (!passwordEncoder.matches(changePassword.getOldPassword(), existingUser.getPassword())) {
            throw new InvalidPassword("password is incorrect");
        }
        validatePassword(changePassword.getNewPassword());
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            throw new InvalidPassword("Confirmation must match the new password");
        }
        String hashedNewPassword = passwordEncoder.encode(changePassword.getNewPassword());
        existingUser.setPassword(hashedNewPassword);
        userRepository.save(existingUser);

        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setMessage("Password changed successfully");

        return response;
    }

    @Override
    public ChangeUserNameResponse changeUserName(ChangeUserNameRequest changeUserName) {
        User existingUser = userRepository.findById(changeUserName.getUserId())
                .orElseThrow(() -> new UserNotFound("User does not exist"));
        existingUser.setUserName(changeUserName.getNewUserName());
        ChangeUserNameResponse response = new ChangeUserNameResponse();
        response.setMessage("User name changed successfully");

        return response;
    }

    private String hashedValue (String value){
        return passwordEncoder.encode(value);
    }
}
