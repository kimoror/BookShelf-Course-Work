package bookshelf.models.services;

import bookshelf.aspect.Loggable;
import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.entities.Order;
import bookshelf.models.entities.Product;
import bookshelf.models.repository.OrderRepo;
import bookshelf.models.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing {@link Product}. Using {@link ProductRepo}
 */
@Service
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Find all products
     *
     * @return products
     */
    @Transactional
    @Loggable
    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public Product findById(Long id){
        return productRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with id '%s' not found", id)));
    }

    @Transactional
    @Loggable
    public void addProduct(Product product){
        System.out.println(product.toString());
        productRepo.save(product);
    }

    @Transactional
    @Loggable
    public List<Product> getProductByProduct_typeId(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeId(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    @Transactional
    @Loggable
    public List<Product> getProductByProduct_typeIdAndOrderByCostAsc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByCostAsc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    @Transactional
    @Loggable
    public List<Product> getProductByProduct_typeIdAndOrderByCostDesc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByCostDesc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    @Transactional
    @Loggable
    public List<Product> getProductByProduct_typeIdAndOrderByNameDesc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByNameDesc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    @Transactional
    @Loggable
    public List<Product> getProductByProduct_typeIdAndOrderByNameAsc(Long id){
        List<Product> productList = productRepo.getProductByProduct_typeIdAndOrderByNameAsc(id);
        if(productList.isEmpty())
            throw new EntityNotFoundException(String.format("Product type with id '%s' not found", id));
        return productList;
    }

    /**
     * Find all products whose id are in idList
     * @param idList - list of product id
     * @return products by id from idList
     */
    @Transactional
    @Loggable
    public List<Product> findAllByIdList(List<Long> idList){
        List<Product> productList = productRepo.findAll();
        List<Product> resultProducts = new ArrayList<>();

        for(int i = 0; i < idList.size(); i++){
            for (Product product : productList) {
                if (product.getId() == i)
                    resultProducts.add(product);
            }
        }

        return resultProducts;
    }
}
