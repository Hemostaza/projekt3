package pl.sdacademy.projekt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.projekt3.entities.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> getAllByUser(String login);
    List<Comment> getAllByUser_Id(int id);

}
