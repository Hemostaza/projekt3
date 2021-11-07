package pl.sdacademy.projekt3.controllers;

import com.sun.xml.bind.v2.runtime.output.Encoded;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.User;
import pl.sdacademy.projekt3.repositories.UserRepository;
import pl.sdacademy.projekt3.services.MemeService;
import pl.sdacademy.projekt3.services.UserServices;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository; // <- całkowicie usunąć w przyszłości i przenieśc wszystko co istotne na userServices;
    private final UserServices userServices;
    //private final CommentRepository commentRepository;
    private final MemeService memeService;

    public UserController(UserRepository userRepository, MemeService memeService, UserServices userServices) {
        this.userRepository = userRepository;
        //this.commentRepository = commentRepository;
        this.memeService = memeService;
        this.userServices = userServices;
    }

    @GetMapping("/list")
    public String getList(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return "user/user_list";
    }

    @GetMapping("/login")
    public String login() {
        return "user/user_login";
    }

    @PostMapping("/login")
    public String postlogin() {
        return "user/user_login";
    }

    @GetMapping("/register")
    public String registerUserForm(@ModelAttribute("user") User user) {
        return "user/user_register";
    }

    @PostMapping("/register")
    public String registerUser(@Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/user_register";
        }
        userServices.save(user);
        return "user/user_register_result";
    }

    @GetMapping("/delete/by-id/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/by-user/{user}")
    public String deleteUser(@PathVariable String user) {
        User byLogin = userRepository.findByLogin(user);
        userRepository.delete(byLogin);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/by-id/{id}")
    public String editUser(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userRepository.findById(id));
        return "user/user_edit";
    }

    @GetMapping("/edit/by-user/{user}")
    public String editUser(@PathVariable String user, ModelMap modelMap) {
        modelMap.addAttribute("user", userRepository.findByLogin(user));
        return "user/user_edit";
    }

    @PostMapping("/edit")
    public String saveEditUser(User user) {
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/promote/{user}")
    public String promote(@PathVariable String user) {
        userServices.promoteToAdmin(userRepository.findByLogin(user));
        return "user/user_register_result";
    }

    @GetMapping("/demote/{user}")
    public String demote(@PathVariable String user) {
        userServices.demoteToUser(userRepository.findByLogin(user));
        return "user/user_register_result";
    }
    //STRONA UZYTKOWNIKA
    //komentarze użytkownika
    //memy użytkownika
    //odnosniki do edycji komentarzy/memów
    //odnosnik do usunięcie konta

    @GetMapping("/mypage")
    @PreAuthorize("isAuthenticated()") //trzeba sie zalogować
    public String userMems(ModelMap modelMap, Authentication authentication) { //Authentication to nasz zalogowany user
        User user = userRepository.findByLogin(authentication.getName()); //zabranie obiektu użytkownika z bazy na podstawie zalogowanego
        modelMap.addAttribute("usermemes", memeService.findAllByUserId(user.getId())); //lista memów użytkownika
        modelMap.addAttribute("usercomments", userServices.findAllComments(user.getId()));

        return "user/userPage";
    }
}