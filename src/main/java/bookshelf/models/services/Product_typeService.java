package bookshelf.models.services;

import bookshelf.aspect.Loggable;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.Product_typeDto;
import bookshelf.models.entities.Product_type;
import bookshelf.models.repository.Product_typeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class Product_typeService {
    final Product_typeRepo product_typeRepo;

    public Product_typeService(Product_typeRepo product_typeRepo) {
        this.product_typeRepo = product_typeRepo;
    }

    /**
     * Find all products_types
     *
     * @return products_types
     */
    @Loggable
    @Transactional
    public List<Product_type> findAll(){
        return product_typeRepo.findAll();
    }
}
