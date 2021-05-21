package bookshelf.controllers;

import bookshelf.exceptions.EnumStatusNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.ProductDto;
import bookshelf.models.enums.OrderStatus;
import bookshelf.models.enums.Role;
import bookshelf.models.services.EmailService;
import bookshelf.models.services.OrderService;
import bookshelf.models.services.ProductService;
import bookshelf.models.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;
    private final UserService userService;
    private final EmailService emailService;

    public AdminController(OrderService orderService,
                           UserService userService, EmailService emailService) {
        this.orderService = orderService;
        this.userService = userService;
        this.emailService = emailService;
    }

    /**
     * this method allow the administrator change the order status {@link OrderStatus}
     * @param id - id of order
     * @param status - new status of order
     */
    @PostMapping("/changeOrderStatus/{id}")
    @ResponseBody()
    @PreAuthorize("hasAuthority('change_order_status')")
    public void changeOrderStatus( @PathVariable long id, @RequestBody() String status){
        //parsing json
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
            throw new EnumStatusNotFoundException("Order status not found.");
        orderService.changeStatus(id, orderStatus);
    }

    /**
     * this method allow the administrator send messages {@link EmailService}
     * @param message - message which admin wants to send
     * @param email - email of the user to whom we want to send the message
     */
    @PostMapping("/sendMessage/{email}")
    @ResponseBody
    @PreAuthorize("hasAuthority('send_message')")
    public void sendMessage(@RequestBody String message, @PathVariable String email){
        //parsing json
        StringBuilder stringBuilder = new StringBuilder(message);
        stringBuilder.delete(0, stringBuilder.indexOf(":") + 2);
        stringBuilder.delete(stringBuilder.lastIndexOf("\""), stringBuilder.length());

        String subject = "Message from admin Bookshelf store";
        emailService.sendMessageBuy(stringBuilder.toString(), email, subject);
    }

    /**
     * this method allow administrator change role of the user {@link Role}
     * @param id - id of the user to whom we want change the role
     * @param roleStr - new user's role
     */
    @PostMapping("/changeUserRole/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('change_user_role')")
    public void changeUserRole(@PathVariable long id,@RequestBody String roleStr){
        //parsing json
        StringBuilder stringBuilder = new StringBuilder(roleStr);
        stringBuilder.delete(0, stringBuilder.indexOf(":") + 2);
        stringBuilder.delete(stringBuilder.indexOf("\""), stringBuilder.length());

        Role role = null;
        if(stringBuilder.toString().equals("ADMIN"))
            role = Role.ADMIN;
        if(stringBuilder.toString().equals("USER"))
            role = Role.USER;
        if(role == null)
            throw new EnumStatusNotFoundException("User role not found.");
        userService.changeUserRole(id, role);
    }
}
