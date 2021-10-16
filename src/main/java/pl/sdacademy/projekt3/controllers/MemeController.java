package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.MemeRepository;

import java.util.List;

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
}
