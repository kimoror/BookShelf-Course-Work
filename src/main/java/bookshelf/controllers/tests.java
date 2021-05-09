package bookshelf.controllers;

import bookshelf.models.dto.*;
import bookshelf.models.repository.ProductInOrderRepo;
import bookshelf.models.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class tests {
    final MakerService makerService;
    final OrderService orderService;
    final Product_typeService product_typeService;
    final ProductService productService;
    final UserService userService;
    final ProductInOrderService productInOrderService;
    final ProductInOrderRepo productInOrderRepo;

    public tests(MakerService makerService, OrderService orderService, Product_typeService product_typeService,
                 ProductService productService, UserService userService, ProductInOrderService productInOrderService, ProductInOrderRepo productInOrderRepo) {
        this.makerService = makerService;
        this.orderService = orderService;
        this.product_typeService = product_typeService;
        this.productService = productService;
        this.userService = userService;
        this.productInOrderService = productInOrderService;
        this.productInOrderRepo = productInOrderRepo;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDto> getOrders(){
        return orderService.findAll();
    }



    @RequestMapping(value = "/makers", method = RequestMethod.GET)
    @ResponseBody
    public List<MakerDto> getMakers(){
        return DtoConverter.makerListToDto(makerService.findAll());
    }

    @RequestMapping(value = "/makers/add", method = RequestMethod.POST)
    @ResponseBody
    public void setMaker(@RequestBody MakerDto makerDto){
        makerService.save(makerDto);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getUsers(){
        return userService.findAll();
    }

    @RequestMapping(value = "/product_types", method = RequestMethod.GET)
    @ResponseBody
    public List<Product_typeDto> getProduct_types(){
        return product_typeService.findAll();
    }

    @RequestMapping(value = "/product_in_order", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductInOrderDto> getProductInOrder(){
        return productInOrderService.findAll();
    }

    @RequestMapping(value = "/product_in_order/id", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductDto> getProductInOrderById(@RequestParam long order_id){
        return productInOrderService.getProductsByOrderId(order_id);
    }

    @GetMapping("/productInOrderSave")
    @ResponseBody
    public void productInOrderSave() {
//        productInOrderService.save(1,3);
        productInOrderRepo.numOfProductInc(1,3);
    }
}
