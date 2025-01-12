package bookShelf.dtos.requests.user;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
    private String username;
}
