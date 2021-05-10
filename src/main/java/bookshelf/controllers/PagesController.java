package bookshelf.controllers;

import bookshelf.models.entities.User;
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

    @GetMapping("books")
    public String books(){
        return "products/books";
    }

    @GetMapping
    public String index(User user){
        return "redirect:/books";
    }

}
