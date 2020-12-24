package com.kong.blog.controller;

import com.kong.blog.service.ServiceImp.UserServiceImp;
import com.kong.blog.domain.User;
import com.kong.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserServiceImp userService;

    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user!=null){
            session.setAttribute("user",user);
            return "admin/index";
        }
        attributes.addFlashAttribute("msg","用户名或密码错误");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/login";
    }
}
