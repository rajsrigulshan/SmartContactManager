package com.example.SmartContactManager.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeService {
    
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title","home");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title","about");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title","signup");
        return "signup";
    }
    

}
