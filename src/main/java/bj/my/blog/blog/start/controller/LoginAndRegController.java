package bj.my.blog.blog.start.controller;

import bj.my.blog.blog.start.util.DESUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAndRegController {
    @RequestMapping("/registerBefore")
    public String registerBefore() {
        //返回一个注册页面模板

        return "";
    }
    @RequestMapping("/register")
    public String register(Model model){
        //注册并返回到首页已登录状态
        model.addAttribute("DES",new DESUtil());
        return "register";
    }
}
