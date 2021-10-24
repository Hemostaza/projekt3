package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.entities.User;
import pl.sdacademy.projekt3.repositories.CommentRepository;
import pl.sdacademy.projekt3.services.CommentService;
import pl.sdacademy.projekt3.services.UserServices;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final UserServices userServices;
    private final CommentService commentService;

    public CommentController(UserServices userServices, CommentService commentService) {
        this.userServices = userServices;
        this.commentService = commentService;
    }

    @GetMapping("/list")
    public String getList(ModelMap modelMap){
        List<Comment> comments = commentService.findAll();
        modelMap.addAttribute("comments",comments);
        return "comment/list";
    }

    @GetMapping("/add")
    public String addComment(ModelMap modelMap, @ModelAttribute("comment") Comment comment){
        List<User> users = userServices.findAll();
        modelMap.addAttribute("users", users);
        return "comment/form";
    }

    @PostMapping("/add")
    public String saveComment(Comment comment){
        commentService.save(comment);
        return "redirect:/comment/list";
    }

    @GetMapping("/delete/by-id/{id}")
    public String deleteCommentById(@PathVariable Integer id){
        commentService.deleteById(id);
        return "redirect:/comment/list";
    }

    @GetMapping("/by-user/{user}")
    public String commentByUser(@PathVariable String userLogin, ModelMap modelMap){
        //commentRepository.findByUser(user);
        List<Comment> comments = commentService.findAllByUserLogin(userLogin);
        modelMap.addAttribute("comments", comments);
        return "comment/list";
    }

//    @GetMapping("/delete/by-user/{user}")
//    public String deleteCommentByUser(@PathVariable String user){
//        int id = commentRepository.
//        commentRepository.deleteById(id);
//        return "redirect:/comment/list";
//    }

    @GetMapping("/edit/by-id/{id}")
    public String editComment(@PathVariable Integer id, ModelMap modelMap){
        modelMap.addAttribute("comment", commentService.findById(id));
        return "comment/editform";
    }

    @PostMapping("/edit")
    public String saveEditComment(Comment comment){
       commentService.save(comment);
        return "redirect:/comment/list";
    }


}
