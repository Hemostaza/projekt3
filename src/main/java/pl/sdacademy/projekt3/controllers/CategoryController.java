package pl.sdacademy.projekt3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.projekt3.entities.Category;
import pl.sdacademy.projekt3.repositories.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/list")
    public String findList(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        return "category/list";
    }
}
