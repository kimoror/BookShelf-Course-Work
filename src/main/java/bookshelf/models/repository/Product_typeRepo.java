package bookshelf.models.repository;

import bookshelf.models.entities.Order;
import bookshelf.models.entities.Product_type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Product_type}
 */
public interface Product_typeRepo extends JpaRepository<Product_type, Long> {
}
