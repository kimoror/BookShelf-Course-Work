package bookshelf.models.repository;

import bookshelf.models.entities.Product_type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product_typeRepo extends JpaRepository<Product_type, Long> {
}
