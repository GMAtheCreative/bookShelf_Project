package bookShelf.services;

import bookShelf.data.repositories.UserRepository;
import bookShelf.dtos.requests.user.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    UserServices userServices;
    UserRepository userRepository;
    @BeforeEach
    void setUp() {
     userRepository.deleteAll();
    }

    @Test
    void registerUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("Timothy");
        request.setPassword("password");
        request.setEmail("timothy@gmail.com");
        userServices.registerUser(request);
        assertTrue(userRepository.existsById(request.getUsername()));
        assertEquals(1, userRepository.count());
    }

    @Test
    void login() {
    }

    @Test
    void profilePic() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void changeUserName() {
    }
}