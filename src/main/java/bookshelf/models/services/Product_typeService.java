package bookshelf.models.services;

import bookshelf.models.entities.Product_type;
import bookshelf.models.repository.Product_typeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product_typeService {
    final Product_typeRepo product_typeRepo;

    public Product_typeService(Product_typeRepo product_typeRepo) {
        this.product_typeRepo = product_typeRepo;
    }

    /**
     *
     * @param product_type entity to save
     */
    public void save(Product_type product_type){
        product_typeRepo.save(product_type);
    }

    /**
     * Find all products_types
     *
     * @return products_types
     */
    public List<Product_type> findAll(){
        return product_typeRepo.findAll();
    }
}
