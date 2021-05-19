package bookshelf.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/ok")
    @ResponseBody()
    @PreAuthorize("hasAuthority('write')")
    public String oke(){
        return "ok I'am admin";
    }
}
