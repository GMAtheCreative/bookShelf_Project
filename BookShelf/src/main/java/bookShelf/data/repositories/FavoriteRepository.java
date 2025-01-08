package bookShelf.data.repositories;

import bookShelf.data.models.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {

}
