package pl.sdacademy.projekt3.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public void save(Comment comment){
        if(comment.getId()!=null){
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Error");
            throw illegalArgumentException;
        }
        commentRepository.save(comment);
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public void deleteById(int id){
        commentRepository.deleteById(id);
    }

    public Optional<Comment> findById(int id){
        return commentRepository.findById(id);
    }

    public List<Comment> findAllByUserId(int id){
        return commentRepository.getAllByUser_Id(id);
    }

    public List<Comment> findAllByUserLogin(String login) {
        return commentRepository.getAllByUser(login);
    }
}
