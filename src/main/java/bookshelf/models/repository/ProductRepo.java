package bookshelf.models.repository;

import bookshelf.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link Product}
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    /**
     * @param id - id of product type
     * @return products that have a product type id as in the parameter
     */
    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id")
    public List<Product> getProductByProduct_typeId(Long id);

    /**
     * @param id - id of product type
     * @return - products that have a product type id as in the parameter sorted by cost ascending
     */
    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.cost ASC")
    public List<Product> getProductByProduct_typeIdAndOrderByCostAsc(Long id);

    /**
     * @param id - id of product type
     * @return - products that have a product type id as in the parameter sorted by cost descending
     */
    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.cost DESC")
    public List<Product> getProductByProduct_typeIdAndOrderByCostDesc(Long id);

    /**
     * @param id - id of product type
     * @return - products that have a product type id as in the parameter sorted by name descending
     */
    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.name DESC")
    public List<Product> getProductByProduct_typeIdAndOrderByNameDesc(Long id);

    /**
     * @param id - id of product type
     * @return - products that have a product type id as in the parameter sorted by cost ascending
     */
    @Query(value = "SELECT p FROM Product p WHERE p.product_type.id = :id ORDER BY p.name ASC")
    public List<Product> getProductByProduct_typeIdAndOrderByNameAsc(Long id);

}
