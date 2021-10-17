package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLoginPage() {
        return "/user/user_login";
    }
}
