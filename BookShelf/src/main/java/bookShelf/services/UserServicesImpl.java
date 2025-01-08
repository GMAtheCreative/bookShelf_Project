package bookShelf.services;


import bookShelf.dtos.requests.user.*;
import bookShelf.dtos.responses.user.*;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUser) {
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
