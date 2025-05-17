package farmsystem.memekly.repository;

import farmsystem.memekly.domain.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<Meme, String> {
}