package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.MemeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/meme")
public class MemeController {
    private final MemeRepository memeRepository;

    public MemeController(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    @GetMapping("/list")
    public String getMemes(ModelMap modelMap){
        List<Meme> memes = memeRepository.findAll();
        modelMap.addAttribute("memes",memes);
        return "meme/memelist";
    }

    @GetMapping("/add")
    public String addMeme(@ModelAttribute("meme") Meme meme){
        return "meme/memeaddform";
    }
    @PostMapping("/add")
    public String saveMeme(Meme meme){
        memeRepository.save(meme);
        return "redirect:/meme/list";
    }

    @GetMapping("/delete/by-id/{id}")
    public String deleteMeme(@PathVariable Integer id){
        memeRepository.deleteById(id);
        return "redirect:/meme/list";
    }
    @GetMapping("/edit/by-id/{id}")
    public String editMeme(@PathVariable Integer id, ModelMap modelMap){
        modelMap.addAttribute("meme",memeRepository.findById(id));
        return "meme/memeedit";
    }
    @PostMapping("/edit")
    public String saveEditMeme(Meme meme){
        memeRepository.save(meme);
        return "redirect:/meme/list";
    }
    @GetMapping("/list/by-user/{id}")
    public String getMemesByUser(@PathVariable String user, ModelMap modelMap){
        List<Meme> memes = memeRepository.findAllByUser(user);
        modelMap.addAttribute("memes",memes);
        return "meme/memelist";
    }
    @GetMapping("/list/by-id/{id}")
    public String getMemesById(@PathVariable Integer id, ModelMap modelMap){
        Meme meme = memeRepository.getById(id);
        if(meme!=null) modelMap.addAttribute("meme",meme);
        return "meme/meme";
    }
}
