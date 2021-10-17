package pl.sdacademy.projekt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.projekt3.entities.Meme;

import java.util.List;

public interface MemeRepository extends JpaRepository<Meme, Integer> {
    List<Meme> findAllByUser(String user);
}
