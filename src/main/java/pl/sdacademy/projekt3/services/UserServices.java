package pl.sdacademy.projekt3.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.entities.User;
import pl.sdacademy.projekt3.repositories.MemeRepository;
import pl.sdacademy.projekt3.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(int id){
        return userRepository.findById(id).orElseThrow(()->new NullPointerException("Nie ma użytkownika o zadanym identyfikatorze"));
    }

    public void addComment(){

    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
