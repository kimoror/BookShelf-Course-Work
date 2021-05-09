package bookshelf.controllers;

import bookshelf.context.OrderContext;
import bookshelf.models.entities.Order;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.services.OrderService;
import bookshelf.models.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    OrderContext orderContext;

    public ShoppingCartController(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

//    @PostMapping("/add/{id}")
    @GetMapping("/add/{id}")
    @ResponseBody
    public void addProductToCart(@PathVariable Long id){
        orderContext.setUser();
        orderContext.addOrder(id);
    }
}
