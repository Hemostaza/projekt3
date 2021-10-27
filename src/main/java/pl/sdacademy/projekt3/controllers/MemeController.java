package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.CommentRepository;
import pl.sdacademy.projekt3.services.MemeService;
import pl.sdacademy.projekt3.services.UserServices;

import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/meme")
public class MemeController {
    private final MemeService memeService;
    private final CommentRepository commentRepository;
    private final UserServices userServices;

    public MemeController(MemeService memeService, UserServices userServices, CommentRepository commentRepository) {
        this.memeService = memeService;
        this.userServices = userServices;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/list")
    public String getMemes(ModelMap modelMap) {
        List<Meme> memes = memeService.findAll();
        modelMap.addAttribute("memes", memes);
        return "meme/memelist";
    }

    @GetMapping("/add/{userId}")
    public String addMeme(ModelMap modelMap, @ModelAttribute("meme") Meme meme, @PathVariable int userId) {
        modelMap.addAttribute("userById", userServices.findById(userId));
        return "meme/memeaddform";
    }

    @PostMapping("/add")
    public String saveMeme(Meme meme) {
        memeService.save(meme);
        return "redirect:/meme/list";
    }

    @GetMapping("/delete/by-id/{id}")
    public String deleteMeme(@PathVariable Integer id) {
        memeService.deleteById(id);
        return "redirect:/meme/list";
    }

    @GetMapping("/edit/by-id/{id}")
    public String editMeme(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("meme", memeService.findById(id));
        return "meme/memeedit";
    }

    @PostMapping("/edit")
    public String saveEditMeme(Meme meme) {
        memeService.save(meme);
        return "redirect:/meme/list";
    }


    //    @GetMapping("/list/by-user/{login}")
//    public String getMemesByUser(@PathVariable String login, ModelMap modelMap){
//        List<Meme> memes = memeService.findAllByUser(login);
//        modelMap.addAttribute("memes",memes);
//        return "meme/memelist";
//    }
    @GetMapping("/list/by-id/{memeId}")
    public String getMemesById(@ModelAttribute("comment") Comment comment, @PathVariable Integer memeId, ModelMap modelMap) {
        Meme meme = memeService.findById(memeId);
        modelMap.addAttribute("meme", meme);
        return "meme/meme";
    }

    @PostMapping("/list/by-id/{memeId}")
    public String addComment(Comment comment, @PathVariable Integer memeId) {
        userServices.addComment(comment, memeId);
        return "redirect:/";
    }
    @GetMapping("/list/by-category/{category}")
    public String getMemesByCategory(@PathVariable String category, ModelMap modelMap){
        List<Meme> memes = memeService.findAllByCategory(category);
        modelMap.addAttribute("memes",memes);
        return "meme/memelist";
    }
//    @GetMapping("/upvote/{id}")
//    public String upvote(Meme meme){
//        Meme meme1 = memeService.getById(meme.getId());
//        meme1.setRating(meme1.getRating()+1);
//        memeService.save(meme1);
//        return "redirect:/";
//    }
//    @GetMapping("/downvote/{id}")
//    public String downvote(Meme meme){
//        Meme meme1 = memeService.getById(meme.getId());
//        meme1.setRating(meme1.getRating()-1);
//        memeService.save(meme1);
//        return "redirect:/";
//    }

    @ModelAttribute("memelist")
    public List<Meme> getMemeList() {
        return memeService.findAll();
    }
}
