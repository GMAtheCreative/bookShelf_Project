package bookShelf.dtos.requests.user;

import lombok.Data;

@Data
public class ChangeUserNameRequest {
    private String userId;
    private String newUserName;

}
