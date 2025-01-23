package bookShelf.controllers;

import bookShelf.dtos.requests.user.RegisterUserRequest;
import bookShelf.dtos.responses.user.RegisterUserResponse;
import bookShelf.exception.userException.UserNotFound;
import bookShelf.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserControllers {
    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam ("email") String email,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password ){
        try{
            RegisterUserRequest registerUser = new RegisterUserRequest();
            registerUser.setEmail(email);
            registerUser.setPassword(password);
            registerUser.setUsername(userName);
            RegisterUserResponse response = userServices.registerUser(registerUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(UserNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
         catch (Exception e) {
            return new ResponseEntity<>("An error occurred while registering user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
