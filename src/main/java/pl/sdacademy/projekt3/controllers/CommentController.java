package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.repositories.CommentRepository;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/list")
    public String getList(ModelMap modelMap){
        List<Comment> comments = commentRepository.findAll();
        modelMap.addAttribute("comments",comments);
        return "comment/list";
    }

    @GetMapping("/add")
    public String addComment(@ModelAttribute("comment") Comment comment){
        return "comment/form";
    }

    @PostMapping("/add")
    public String saveComment(Comment comment){
        commentRepository.save(comment);
        return "redirect:/comment/list";
    }

    @GetMapping("/delete/by-id/{id}")
    public String deleteCommentById(@PathVariable Integer id){
        commentRepository.deleteById(id);
        return "redirect:/comment/list";
    }

    @GetMapping("/by-user/{user}")
    public String commentByUser(@PathVariable String user, ModelMap modelMap){
        //commentRepository.findByUser(user);
        List<Comment> comments = commentRepository.findByUser(user);
        modelMap.addAttribute("comments",comments);
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
        modelMap.addAttribute("comment", commentRepository.findById(id));
        return "comment/editform";
    }

    @PostMapping("/edit")
    public String saveEditComment(Comment comment){
       commentRepository.save(comment);
        return "redirect:/comment/list";
    }


}
