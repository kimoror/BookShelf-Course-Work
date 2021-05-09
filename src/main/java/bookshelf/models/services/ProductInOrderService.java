package bookshelf.models.services;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.dto.ProductInOrderDto;
import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.repository.ProductInOrderRepo;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
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
     * @param productInOrder dto to save
     */
    @Transactional
    public void save(ProductInOrder productInOrder){
        if(existByOrder_idAndProduct_Id(productInOrder.getOrder_id(), productInOrder.getProduct_id()))
            productInOrderRepo.numOfProductInc(productInOrder.getOrder_id(), productInOrder.getProduct_id());
        else
            productInOrderRepo.save(productInOrder);
    }

    /**
     * Find all productInOrderRepo
     *
     * @return products and Orders
     */
    @Transactional
    public List<ProductInOrderDto> findAll(){
        return DtoConverter.productInOrderListToDto(productInOrderRepo.findAll());
    }

    @Transactional
    public List<ProductDto> getProductsByOrderId(long order_id){
        return DtoConverter.productListToDtos(productInOrderRepo.getProductsByOrderId(order_id));
    }

    @Transactional
    public Boolean existByOrder_idAndProduct_Id(long order_id, long product_id){
        return productInOrderRepo.findAllByOrder_idAndProduct_Id(order_id, product_id).size() > 0;
    }

    public List<Long> getAllProduct_idByOrderId(long order_id){
        return productInOrderRepo.getAllProduct_idByOrderId(order_id);
    }

    public List<Product> getProductsByOrder(long order_id){
        return productInOrderRepo.getProductsByOrder(order_id);
    }

}
