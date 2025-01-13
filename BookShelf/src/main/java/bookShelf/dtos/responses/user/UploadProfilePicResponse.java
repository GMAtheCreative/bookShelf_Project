package bookShelf.dtos.responses.user;

import lombok.Data;
@Data
public class UploadProfilePicResponse {
    private String message;
    private byte[] profilePic;
}
