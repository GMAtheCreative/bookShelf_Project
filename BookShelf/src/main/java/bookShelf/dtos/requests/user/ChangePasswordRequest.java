package bookShelf.dtos.requests.user;

import lombok.Data;

@Data

public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String email;
    private String userId;
}
