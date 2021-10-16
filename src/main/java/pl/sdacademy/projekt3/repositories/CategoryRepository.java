package pl.sdacademy.projekt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.projekt3.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
