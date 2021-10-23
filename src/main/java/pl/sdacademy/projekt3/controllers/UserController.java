package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.entities.User;
import pl.sdacademy.projekt3.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String getList(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return "user/user_list";
    }

    @GetMapping("/register")
    public String registerUserForm(@ModelAttribute("user") User user) {
        return "user/user_register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userRepository.save(user);
        return "user/user_register_result";
    }

    @GetMapping("/delete/by-id/{id}")
    public String deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/by-id/{id}")
    public String editUser(@PathVariable Integer id, ModelMap modelMap){
        modelMap.addAttribute("user",userRepository.findById(id));
        return "user/user_edit";
    }
    @PostMapping("/edit")
    public String saveEditUser(User user){
        userRepository.save(user);
        return "redirect:/user/list";
    }
}