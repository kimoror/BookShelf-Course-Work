package bookshelf.models.services;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.Product_typeDto;
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
     * @param product_typeDto dto to save
     */
    public void save(Product_typeDto product_typeDto){
        product_typeRepo.save(DtoConverter.dtoToProduct_type(product_typeDto));
    }

    /**
     * Find all products_types
     *
     * @return products_types
     */
    public List<Product_typeDto> findAll(){
        return DtoConverter.product_typeListToDtos(product_typeRepo.findAll());
    }
}
