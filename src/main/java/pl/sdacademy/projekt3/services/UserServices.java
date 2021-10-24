package pl.sdacademy.projekt3.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.projekt3.entities.User;
import pl.sdacademy.projekt3.repositories.UserRepository;

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

}
