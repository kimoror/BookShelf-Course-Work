package bookshelf.controllers;

import bookshelf.models.entities.*;
import bookshelf.models.repository.Product_typeRepo;
import bookshelf.models.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class tests {
    final MakerService makerService;
    final OrderService orderService;
    final Product_typeRepo product_typeService;
    final ProductService productService;
    final UserService userService;
    final ProductInOrderService productInOrderService;

    public tests(MakerService makerService, OrderService orderService, Product_typeRepo product_typeService,
                 ProductService productService, UserService userService, ProductInOrderService productInOrderService) {
        this.makerService = makerService;
        this.orderService = orderService;
        this.product_typeService = product_typeService;
        this.productService = productService;
        this.userService = userService;
        this.productInOrderService = productInOrderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrders(){
        return orderService.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @RequestMapping(value = "/makers", method = RequestMethod.GET)
    @ResponseBody
    public List<Maker> getMakers(){
        return makerService.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(){
        return userService.findAll();
    }

    @RequestMapping(value = "/product_types", method = RequestMethod.GET)
    @ResponseBody
    public List<Product_type> getProduct_types(){
        return product_typeService.findAll();
    }

    @RequestMapping(value = "/product_in_order", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductInOrder> getProductInOrder(){
        return productInOrderService.findAll();
    }

    @RequestMapping(value = "/product_in_order/id", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductInOrderById(@RequestParam long order_id){
        return productInOrderService.getProductsByOrderId(order_id);
    }
}
