package bookShelf.services;

import bookShelf.data.models.User;
import bookShelf.data.repositories.UserRepository;
import bookShelf.dtos.requests.user.ChangeUserNameRequest;
import bookShelf.dtos.requests.user.LoginUserRequest;
import bookShelf.dtos.requests.user.RegisterUserRequest;
import bookShelf.dtos.responses.user.ChangeUserNameResponse;
import bookShelf.dtos.responses.user.LoginUserResponse;
import bookShelf.dtos.responses.user.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void profilePic() {
    }

    @Test
    void changePassword() {
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