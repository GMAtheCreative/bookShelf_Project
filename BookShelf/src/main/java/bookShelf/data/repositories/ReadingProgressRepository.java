package bookShelf.data.repositories;

import bookShelf.data.models.ReadingProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingProgressRepository extends MongoRepository<ReadingProgress, String> {
}
