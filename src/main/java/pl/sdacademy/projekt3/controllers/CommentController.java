package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        modelMap.addAttribute("comment",comments);
        return "/comment/list";
    }

    @GetMapping("add")
    public String getForm(@ModelAttribute("comment") Comment comment){
        return "/comment/form";
    }

    @PostMapping("/add")
    public String create(Comment comment){
        commentRepository.save(comment);
        return "forward:/comment/comment/list";
    }
}
