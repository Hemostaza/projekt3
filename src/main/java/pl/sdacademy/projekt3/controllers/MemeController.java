package pl.sdacademy.projekt3.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.CommentRepository;
import pl.sdacademy.projekt3.services.MemeService;
import pl.sdacademy.projekt3.services.UserServices;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

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

    //Pobranie z bazy danych obrazka w celu wyświetlenia go
    //wersja do poprawy ale działa
    @GetMapping("/download/{id}")
    public void downloadImage(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        //Mem ktory chce wysweitlic obrazek
        Meme meme = memeService.findById(id);
        //przypisanie obrazka z bazy danych do tablicy
        byte[] image = meme.getImage();
        //Wynik z http responsa
        response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        response.setHeader("Image", "Meme title =" + meme.getTitle());
        response.setContentLength(image.length);
        //wypisanie obrazka
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(image, 0, image.length);
        outputStream.close();
    }

    //wersja lepsza ale do poprawy z wyświetlaniem :>
    //       @GetMapping(path ="/download/{id}",produces = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE )
//    public byte[] downloadImage(@PathVariable Integer id) throws IOException {
//        //Mem ktory chce wysweitlic obrazek
//        Meme meme = memeService.findById(id);
//        //przypisanie obrazka z bazy danych do tablicy
//        return meme.getImage();


    @GetMapping("/list")
    public String getMemes(ModelMap modelMap) {
        List<Meme> memes = memeService.findAll();
        modelMap.addAttribute("memes", memes);
        return "meme/memelist";
    }

    @GetMapping("/add")
    public String addMeme(ModelMap modelMap, @ModelAttribute("meme") Meme memes, Authentication authentication) {
        modelMap.addAttribute("user", userServices.findByLogin(authentication.getName()));
        return "meme/memeaddform";
    }

    @PostMapping("/add")
    public String saveMeme(Meme meme, @RequestParam("file") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new Exception("Plik jest pusty lub go nie ma");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new Exception("Plik nie jest obrazkiem");
        }
        memeService.save(meme, file);
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
    public String addComment(Comment comment, @PathVariable Integer memeId, ModelMap modelMap, Authentication authentication) {
        userServices.addComment(comment, memeId, authentication);
        modelMap.addAttribute("meme", memeService.findById(memeId));
        return "redirect:/meme/list/by-id/{memeId}";
    }

    @GetMapping("/list/by-category/{category}")
    public String getMemesByCategory(@PathVariable String category, ModelMap modelMap) {
        List<Meme> memes = memeService.findAllByCategory(category);
        modelMap.addAttribute("memes", memes);
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
