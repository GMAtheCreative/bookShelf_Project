package bookShelf.services;


import bookShelf.data.models.User;
import bookShelf.data.repositories.UserRepository;
import bookShelf.dtos.requests.user.*;
import bookShelf.dtos.responses.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUser) {
        User user = new User();
        user.setUserName(registerUser.getUsername());
        user.setPassword(registerUser.getPassword());
        user.setEmail(registerUser.getEmail());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(registerUser.getUsername() +" is registered successfully");
        return null;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginUser) {
        return null;
    }

    @Override
    public UploadProfilePicResponse profilePic(UploadProfilePicRequest profilePic) {
        return null;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePassword) {
        return null;
    }

    @Override
    public ChangeUserNameResponse changeUserName(ChangeUserNameRequest changeUserName) {
        return null;
    }
}
