package bookshelf.controllers;

import bookshelf.context.OrderContext;
import bookshelf.models.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    OrderContext orderContext;

    public ShoppingCartController(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    //TODO когда будешь прикручивать фронт, сделай post
//    @PostMapping("/add/{id}")
    @GetMapping("/product/add/{id}")
    @ResponseBody
    public void addProductToCart(@PathVariable Long id){
        orderContext.setUser();
        orderContext.addOrder(id);
    }

    @GetMapping("/getOrder")
    @ResponseBody
    public Map<Product, Integer> getOrder(){
        orderContext.setUser();
        return orderContext.getProductsFromOrder();
    }

    @GetMapping("/product/delete/{id}")
    @ResponseBody
    public void deleteProductFromOrder(@PathVariable Long id){
        orderContext.setUser();
        orderContext.deleteProductFromOrder(id);
    }

    @GetMapping("/product/desc/{id}")
    @ResponseBody
    public void descNumOfProduct(@PathVariable Long id){
        orderContext.setUser();
        orderContext.numOfProductDesc(id);
    }

    @GetMapping("/buyOrder")
    @ResponseBody
    public void buyOrder(){
        orderContext.setUser();
        orderContext.buyOrder();
    }

    @GetMapping("/cancelOrder")
    @ResponseBody
    public void cancelOrder(){
        orderContext.setUser();
        orderContext.cancelOrder();
    }


}
