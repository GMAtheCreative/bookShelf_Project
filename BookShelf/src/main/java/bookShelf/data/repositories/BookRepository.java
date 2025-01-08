package bookShelf.data.repositories;

import bookShelf.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findAllByUserId(String userId);

    List<Book> findByUserIdAndTitle(String userId, String title);

    List<Book> findByUserIdAndAuthor(String userId, String Author);

    Optional<Book> findByUserIdAndTitleAndAuthor(String userId, String originalTitle, String originalAuthor);
}
