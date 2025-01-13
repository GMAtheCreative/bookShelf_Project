package bookShelf.dtos.requests.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data

public class UploadProfilePicRequest {
    private String userId;
    private MultipartFile picture;
}
