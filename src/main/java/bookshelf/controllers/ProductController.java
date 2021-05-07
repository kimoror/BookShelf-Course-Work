package bookshelf.controllers;

import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.entities.Product;
import bookshelf.models.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/orderByNameAsc")
    @ResponseBody
    public List<ProductDto> orderProductsByName(){
        return DtoConverter.productListToDtos(productService.findAllSortByNameAsc());
    }

    @GetMapping("product-type-id/{id}")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeId(@PathVariable Long id){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeId(id));
    }

    @GetMapping("product-type-id/{id}/orderByCostAsc")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByCostAsc(@PathVariable Long id){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByCostAsc(id));
    }

    @GetMapping("product-type-id/{id}/orderByCostDesc")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByCostDesc(@PathVariable Long id){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByCostDesc(id));
    }

    @GetMapping("product-type-id/{id}/orderByNameDesc")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByNameDesc(@PathVariable Long id){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByNameDesc(id));
    }

    @GetMapping("product-type-id/{id}/orderByNameAsc")
    @ResponseBody
    public List<ProductDto> getProductByProduct_typeIdAndOrderByNameAsc(@PathVariable Long id){
        return DtoConverter.productListToDtos(productService.getProductByProduct_typeIdAndOrderByNameAsc(id));
    }

}
