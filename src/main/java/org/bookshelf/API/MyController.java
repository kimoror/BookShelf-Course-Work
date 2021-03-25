package org.bookshelf.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookshelf/")
public class MyController {

    @RequestMapping(value = "mainpage", method = RequestMethod.GET)
    public String getMainpage(){
        return "Mainpage";
    }

    @RequestMapping(value = "сlassic", method = RequestMethod.GET)
    public String getClassic(){
        return "Classic";
    }

    @RequestMapping(value = "stationery", method = RequestMethod.GET)
    public String getstationary(){
        return "stationary";

    }

    @RequestMapping(value = "toSchool", method = RequestMethod.GET)
    public String getSchool(){
        return "toSchool";
    }

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String getAbout(){
        return "about";
    }
}
