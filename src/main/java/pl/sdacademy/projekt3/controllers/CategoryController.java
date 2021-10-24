package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projekt3.entities.Category;
import pl.sdacademy.projekt3.repositories.CategoryRepository;

import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/list")
    public String getCategories(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        return "category/list";
    }

    @ModelAttribute ("categorylist")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/add")
    public String getForm(@ModelAttribute("category") Category category) {
        return "category/form";
    }

    @PostMapping("/add")
    public String create(Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }

    @GetMapping("/edit/by-id/{id}")
    public String edit(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryRepository.findById(id));
        return "category/editedlist";
    }
    @GetMapping("/delete/by-id/{id}")
    public String delete(@PathVariable Integer id){
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String saveEditedCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }
}
