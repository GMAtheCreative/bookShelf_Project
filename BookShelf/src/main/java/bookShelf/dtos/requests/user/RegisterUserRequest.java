package bookShelf.dtos.requests.user;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String email;
}
