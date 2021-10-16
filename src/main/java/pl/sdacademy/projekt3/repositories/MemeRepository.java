package pl.sdacademy.projekt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.projekt3.entities.Meme;

public interface MemeRepository extends JpaRepository<Meme, Integer> {
}
