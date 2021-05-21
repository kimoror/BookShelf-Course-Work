package bookshelf.controllers;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.entities.Product;
import bookshelf.models.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for actions with products
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductDto> getProducts(){
        return DtoConverter.productListToDtos(productService.findAll());
    }

    @GetMapping("/{productTypeId}")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeId(@PathVariable Long productTypeId){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeId(productTypeId));
    }

    @GetMapping("/orderByCostAsc/{productTypeId}")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByCostAsc(@PathVariable Long productTypeId){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByCostAsc(productTypeId));
    }

    @GetMapping("/orderByCostDesc/{productTypeId}")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByCostDesc(@PathVariable Long productTypeId){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByCostDesc(productTypeId));
    }

    @GetMapping("/orderByNameDesc/{productTypeId}")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByNameDesc(@PathVariable Long productTypeId){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByNameDesc(productTypeId));
    }

    @GetMapping("/orderByNameAsc/{productTypeId}")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByNameAsc(@PathVariable Long productTypeId){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByNameAsc(productTypeId));
    }

}
