package bookshelf.controllers;

import bookshelf.models.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class PagesController {
    @GetMapping("auth/login")
    public String getLoginPage(){
        return "auth/login";
    }

    @GetMapping("auth/success")
    public String getSuccessPage(){
        return "auth/success";
    }

    @GetMapping("auth/registration")
    public String registration(User user){
        return "auth/registration";
    }

    @GetMapping("views/books")
    public String books(){
        return "products/books";
    }

    @GetMapping("views/stationary")
    public String stationary(){
        return "products/stationary";
    }

    @GetMapping("views/exerciseBooks")
    public String exerciseBooks(){
        return "products/exerciseBooks";
    }

    @GetMapping
    public String index(User user){
        return "redirect:/views/books";
    }

    @GetMapping("shoppingCart")
    public String shoppingCart(){return "products/shoppingCart"; }

    @GetMapping("admin")
    public String admin(){
        return "admin/admin";
    }

}
