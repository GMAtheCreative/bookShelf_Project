package bookShelf.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Users")
@Data
public class User {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private byte[] profilePicture;
}
