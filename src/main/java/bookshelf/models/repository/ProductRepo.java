package bookshelf.models.repository;

import bookshelf.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByNameAsc();

    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id")
    public List<Product> getProductByProduct_typeId(Long id);

    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.cost ASC")
    public List<Product> getProductByProduct_typeIdAndOrderByCostAsc(Long id);

    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.cost DESC")
    public List<Product> getProductByProduct_typeIdAndOrderByCostDesc(Long id);

    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.name DESC")
    public List<Product> getProductByProduct_typeIdAndOrderByNameDesc(Long id);

    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.name ASC")
    public List<Product> getProductByProduct_typeIdAndOrderByNameAsc(Long id);

}
