package bookshelf.controllers;

import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    final OrderService orderService;
    final ProductInOrder productInOrder;

    public ShoppingCartController(OrderService orderService, ProductInOrder productInOrder) {
        this.orderService = orderService;
        this.productInOrder = productInOrder;
    }

    @PostMapping("/add/{id}")
    @ResponseBody
    public void addProductToCart(@PathVariable Long id){

    }
}
