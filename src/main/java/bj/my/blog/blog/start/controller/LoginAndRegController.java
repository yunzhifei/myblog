package bj.my.blog.blog.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAndRegController {
    @RequestMapping("/registerBefore")
    public String registerBefore() {
        //返回一个注册页面模板
        return "";
    }
    @RequestMapping("register")
    public String register(){
        //注册并返回到首页已登录状态想
        return "redirect:/";
    }
}
