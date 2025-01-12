package bookShelf.data.repositories;

import bookShelf.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findUserByUserName(String username);
}
