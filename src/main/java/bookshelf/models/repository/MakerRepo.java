package bookshelf.models.repository;

import bookshelf.models.entities.Maker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Maker}
 */
@Repository
public interface MakerRepo extends JpaRepository<Maker, Long> {
}
