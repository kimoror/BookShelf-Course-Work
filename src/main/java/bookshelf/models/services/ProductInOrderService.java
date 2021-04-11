package bookshelf.models.services;

import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.repository.ProductInOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing {@link ProductInOrder}
 */
@Service
public class ProductInOrderService {
    final ProductInOrderRepo productInOrderRepo;

    public ProductInOrderService(ProductInOrderRepo productInOrderRepo) {
        this.productInOrderRepo = productInOrderRepo;
    }

    /**
     *
     * @param productInOrder entity to save
     */
    public void save(ProductInOrder productInOrder){
        productInOrderRepo.save(productInOrder);
    }

    /**
     * Find all productInOrderRepo
     *
     * @return products and Orders
     */
    public List<ProductInOrder> findAll(){
        return productInOrderRepo.findAll();
    }

    public List<Product> getProductsByOrderId(long order_id){
        return productInOrderRepo.getProductsByOrderId(order_id);
    }

}
