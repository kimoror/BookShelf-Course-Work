package bookshelf.models.services;

import bookshelf.models.entities.Product;
import bookshelf.models.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     *
     * @param product entity to save
     */
    public void save(Product product){
        productRepo.save(product);
    }

    /**
     * Find all products
     *
     * @return products
     */
    public List<Product> findAll(){
        return productRepo.findAll();
    }
}
