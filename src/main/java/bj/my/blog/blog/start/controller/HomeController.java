package bj.my.blog.blog.start.controller;

import bj.my.blog.blog.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name","dear");
        userService.getAllUser();
        System.out.println("userService = " + userService.getAllUser());
        return "index";
    }
}
