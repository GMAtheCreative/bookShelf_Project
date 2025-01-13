package bookShelf.services;

import bookShelf.data.models.User;
import bookShelf.data.repositories.UserRepository;
import bookShelf.dtos.requests.user.*;
import bookShelf.dtos.responses.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    UserServices userServices;
    @Autowired
    UserRepository userRepository;
    @BeforeEach
    void setUp() {
     userRepository.deleteAll();
    }
    private static RegisterUserRequest registerUser1 (RegisterUserRequest registerUserRequest) {
        registerUserRequest.setUsername("Timothy");
        registerUserRequest.setPassword("@Password1");
        registerUserRequest.setEmail("Timothy@gmail.com");
        return registerUserRequest;
    }
    private static LoginUserRequest loginUserWithUsername (LoginUserRequest loginUserRequest) {
        loginUserRequest.setUsername("Timothy");
        loginUserRequest.setPassword("@Password1");
        return loginUserRequest;
    }
    private static LoginUserRequest loginUserWithEmail (LoginUserRequest loginUserRequest) {
        loginUserRequest.setEmail("Timothy@gmail.com");
        loginUserRequest.setPassword("@Password1");
        return loginUserRequest;
    }

    @Test
    void registerUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        RegisterUserRequest register1User = registerUser1(request);
        RegisterUserResponse registerNewUser = userServices.registerUser(register1User);
        assertEquals(1, userRepository.count());
        assertEquals("Timothy Welcome to BookShelf", registerNewUser.getMessage());
    }

    @Test
    void loginWithUsername() {
        RegisterUserRequest request = new RegisterUserRequest();
        RegisterUserRequest registerUser = registerUser1(request);
        userServices.registerUser(registerUser);
        assertEquals(1, userRepository.count());

        LoginUserRequest usernameLogin = new LoginUserRequest();
        LoginUserRequest loginUser = loginUserWithUsername(usernameLogin);
        LoginUserResponse userResponse = userServices.loginWithUsername(loginUser);

        assertEquals(1, userRepository.count());
        assertEquals("Timothy Welcome.... Lets get reading", userResponse.getMessage());
    }

    @Test
    void loginWithEmail() {
        RegisterUserRequest request = new RegisterUserRequest();
        RegisterUserRequest registerUser = registerUser1(request);
        userServices.registerUser(registerUser);
        assertEquals(1, userRepository.count());

        LoginUserRequest emailLogin = new LoginUserRequest();
        LoginUserRequest loginUser = loginUserWithEmail(emailLogin);
        LoginUserResponse userResponse = userServices.loginWithEmail(loginUser);

        assertEquals(1, userRepository.count());
        assertEquals("Timothy@gmail.com Welcome.... Lets get reading", userResponse.getMessage());
    }


    @Test
    void profilePic() throws IOException {
        RegisterUserRequest request = new RegisterUserRequest();
        RegisterUserRequest registerUser = registerUser1(request);
        userServices.registerUser(registerUser);
        assertEquals(1, userRepository.count());

        LoginUserRequest emailLogin = new LoginUserRequest();
        LoginUserRequest loginUser = loginUserWithEmail(emailLogin);
        LoginUserResponse userResponse = userServices.loginWithEmail(loginUser);

        MultipartFile mockFile = new MockMultipartFile
                ("picture", "profile-pic.jpg",
                        "image/jpeg", "test image content".getBytes());
        UploadProfilePicRequest pictureRequest = new UploadProfilePicRequest();
        pictureRequest.setUserId(userResponse.getId());
        pictureRequest.setPicture(mockFile);

        UploadProfilePicResponse response = userServices.profilePic(pictureRequest);
        User updatedUser = userRepository.findById(userResponse.getId()).orElse(null);

        assertNotNull(updatedUser);
        assertNotNull(updatedUser.getProfilePicture());
        assertArrayEquals(mockFile.getBytes(), updatedUser.getProfilePicture());
        assertEquals("Profile picture uploaded successfully", response.getMessage());
    }

    @Test
    void changePassword() {
        RegisterUserRequest request = new RegisterUserRequest();
        RegisterUserRequest registerUser = registerUser1(request);
        userServices.registerUser(registerUser);
        assertEquals(1, userRepository.count());

        LoginUserRequest emailLogin = new LoginUserRequest();
        LoginUserRequest loginUser = loginUserWithEmail(emailLogin);
        LoginUserResponse userResponse = userServices.loginWithEmail(loginUser);

        ChangePasswordRequest passwordRequest = new ChangePasswordRequest();
        passwordRequest.setOldPassword("@Password1");
        passwordRequest.setNewPassword("@Password2");
        passwordRequest.setConfirmPassword("@Password2");
        passwordRequest.setUserId(userResponse.getId());
        ChangePasswordResponse passwordResponse = userServices.changePassword(passwordRequest);

        assertEquals(1, userRepository.count());
        assertEquals("Password changed successfully", passwordResponse.getMessage());
    }

    @Test
    void changeUserName() {
        RegisterUserRequest request = new RegisterUserRequest();
        RegisterUserRequest registerUser = registerUser1(request);
        userServices.registerUser(registerUser);
        assertEquals(1, userRepository.count());

        LoginUserRequest emailLogin = new LoginUserRequest();
        LoginUserRequest loginUser = loginUserWithEmail(emailLogin);
        LoginUserResponse userResponse = userServices.loginWithEmail(loginUser);

        ChangeUserNameRequest changeUserNameRequest = new ChangeUserNameRequest();
        changeUserNameRequest.setUserId(userResponse.getId());
        changeUserNameRequest.setNewUserName("alpha");
        ChangeUserNameResponse changeUserNameResponse = userServices.changeUserName(changeUserNameRequest);

        assertEquals(1, userRepository.count());
        assertEquals("User name changed successfully", changeUserNameResponse.getMessage());

    }
}