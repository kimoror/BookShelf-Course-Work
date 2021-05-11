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

    @GetMapping("/product/add/{id}")
    @ResponseBody
    public String addProductToCart(@PathVariable Long id){
        orderContext.setUser();
        orderContext.addOrder(id);
        return "Ok";
    }

    @GetMapping("/getOrder")
    @ResponseBody
    public Map<Product, Integer> getOrder(){
        orderContext.setUser();
        return orderContext.getProductsFromOrder();
    }

    @GetMapping("/product/delete/{id}")
    @ResponseBody
    public String deleteProductFromOrder(@PathVariable Long id){
        orderContext.setUser();
        orderContext.deleteProductFromOrder(id);
        return "Ok";
    }

    @GetMapping("/product/desc/{id}")
    @ResponseBody
    public String descNumOfProduct(@PathVariable Long id){
        orderContext.setUser();
        orderContext.numOfProductDesc(id);
        return "Ok";
    }

    @GetMapping("/buyOrder")
    @ResponseBody
    public String buyOrder(){
        orderContext.setUser();
        orderContext.buyOrder();
        return "Ok";
    }

    @GetMapping("/cancelOrder")
    @ResponseBody
    public String cancelOrder(){
        orderContext.setUser();
        orderContext.cancelOrder();
        return "Ok";
    }


}
