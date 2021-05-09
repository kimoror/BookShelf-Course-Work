package bookshelf.controllers;

import bookshelf.models.entities.User;
import bookshelf.security.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class RegistrationController {
    final UserDetailsServiceImpl userDetailsService;

    public RegistrationController(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public void registration(User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setAddress(user.getAddress());
        newUser.setAge(user.getAge());
        newUser.setPhone_number(user.getPhone_number());
        newUser.setName(user.getName());
        userDetailsService.addNewUser(newUser);
    }
}
