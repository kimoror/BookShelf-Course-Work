package bookshelf.models.services;

import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
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
     * @param productDto dto to save
     */

    public void save(ProductDto productDto){
        productRepo.save(DtoConverter.dtoToProduct(productDto));
    }

    /**
     * Find all products
     *
     * @return products
     */
    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public List<Product> findAllSortByNameAsc(){
        return productRepo.findAllByOrderByNameAsc();
    }

    public List<Product> getProductByProduct_typeId(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeId(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    public List<Product> getProductByProduct_typeIdAndOrderByCostAsc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByCostAsc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    public List<Product> getProductByProduct_typeIdAndOrderByCostDesc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByCostDesc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    public List<Product> getProductByProduct_typeIdAndOrderByNameDesc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByNameDesc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    public List<Product> getProductByProduct_typeIdAndOrderByNameAsc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByNameAsc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }
}
