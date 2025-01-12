package bookShelf.services;

import bookShelf.dtos.requests.user.*;
import bookShelf.dtos.responses.user.*;

public interface UserServices {
    public RegisterUserResponse registerUser (RegisterUserRequest registerUser);
    public LoginUserResponse loginWithUsername (LoginUserRequest loginUser);
    public LoginUserResponse loginWithEmail (LoginUserRequest loginUser);
    public UploadProfilePicResponse profilePic (UploadProfilePicRequest profilePic);
    public ChangePasswordResponse changePassword (ChangePasswordRequest changePassword);
    public ChangeUserNameResponse changeUserName (ChangeUserNameRequest changeUserName);

}
