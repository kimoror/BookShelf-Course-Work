package bookshelf.controllers;

import bookshelf.exceptions.OrderStatusNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.entities.Order;
import bookshelf.models.entities.Product;
import bookshelf.models.enums.OrderStatus;
import bookshelf.models.services.OrderService;
import bookshelf.models.services.ProductService;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;

    public AdminController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping("/addProduct")
    @ResponseBody()
    @PreAuthorize("hasAuthority('add_product')")
    public void addProduct(@RequestBody ProductDto productDto){
        System.out.println(productDto.toString());
        productService.addProduct(DtoConverter.dtoToProduct(productDto));
    }

    @DeleteMapping("/deleteProduct")
    @ResponseBody()
    @PreAuthorize("hasAuthority('delete_product')")
    public void deleteProduct(@RequestBody long id){

    }

    @PostMapping("/changeOrderStatus/{id}")
    @ResponseBody()
    @PreAuthorize("hasAuthority('change_order_status')")
    public void changeOrderStatus( @PathVariable long id, @RequestBody() String status){
        StringBuilder stringBuilder = new StringBuilder(status);
        stringBuilder.delete(0, stringBuilder.indexOf(":") + 2);
        stringBuilder.delete(stringBuilder.indexOf("\""), stringBuilder.length());
        OrderStatus orderStatus = null;
        if(stringBuilder.toString().equals("ACTIVE"))
            orderStatus = OrderStatus.ACTIVE;
        if(stringBuilder.toString().equals("CLOSE"))
            orderStatus = OrderStatus.CLOSE;
        if(stringBuilder.toString().equals("CANCELED"))
            orderStatus = OrderStatus.CANCELED;
        if(orderStatus == null)
            throw new OrderStatusNotFoundException();
        orderService.changeStatus(id, orderStatus);
    }

    @PostMapping("/sendMessage")
    @ResponseBody
    @PreAuthorize("hasAuthority('send_message')")
    public void sendMessage(@RequestBody long id, String message){

    }

    @PostMapping("/changeUserRole")
    @ResponseBody
    @PreAuthorize("hasAuthority('change_user_role')")
    public void changeUserRole(@RequestBody long id, String role){

    }
}
