package bookshelf.controllers;

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


    @PostMapping("/add/{id}")
//    @GetMapping("/add/{id}")
    @ResponseBody
    public void addProductToCart(@PathVariable Long id){

//       if(orderService.isExistOrderByUser_Id(user_id)){
//           Order newOrder = new Order();
//           newOrder.setUser(userService.findByEmail(userEmail));
//           orderService.save(newOrder);
//       }
    }
}
