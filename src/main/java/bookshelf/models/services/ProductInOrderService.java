package bookshelf.models.services;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.dto.ProductInOrderDto;
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
     * @param productInOrderDto dto to save
     */
    public void save(ProductInOrderDto productInOrderDto){
        productInOrderRepo.save(DtoConverter.dtoToProductInOrder(productInOrderDto));
    }

    /**
     * Find all productInOrderRepo
     *
     * @return products and Orders
     */
    public List<ProductInOrderDto> findAll(){
        return DtoConverter.productInOrderListToDto(productInOrderRepo.findAll());
    }

    public List<ProductDto> getProductsByOrderId(long order_id){
        return DtoConverter.productListToDtos(productInOrderRepo.getProductsByOrderId(order_id));
    }

}
