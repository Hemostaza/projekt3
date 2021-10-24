package pl.sdacademy.projekt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.projekt3.entities.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByUser (String user);
    List<Comment> getAllByUser(String user);

}
