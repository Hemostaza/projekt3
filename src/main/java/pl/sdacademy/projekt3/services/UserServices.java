package pl.sdacademy.projekt3.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdacademy.projekt3.Role;
import pl.sdacademy.projekt3.entities.Comment;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.entities.User;
import pl.sdacademy.projekt3.repositories.CommentRepository;
import pl.sdacademy.projekt3.repositories.MemeRepository;
import pl.sdacademy.projekt3.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServices implements UserDetailsService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final MemeRepository memeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, CommentRepository commentRepository, MemeRepository memeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.memeRepository = memeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(int id){
        return userRepository.findById(id).orElseThrow(()->new NullPointerException("Nie ma użytkownika o zadanym identyfikatorze"));
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    public void promoteToAdmin(User user){
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
    }

    public void demoteToUser(User user){
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }


    //Umieściłem dodawanie komentarza w UserServic bo wydaje mi się,
    //że potem przyda się to w tym miejscu jak będziemy robić security
    //aczkolwiek wrazie czego zawsze można to przenieść lub nawet rozbić na poszczegulne serwisy
    //np nie wysyłać tu int memeId a samego mema żeby nie szukać już go w repo
    //zawsze da się kod poprawić narazie niech działa
    public void addComment(Comment comment, int memeId){
        //znalezienie mema do którego tyczy się komentarz
        Meme meme = memeRepository.findById(memeId).orElseThrow(()-> new NullPointerException("Nie ma mema o id"));
        //dodanie komentarza
        meme.getComments().add(comment);
        //zapisanie komentarza
        commentRepository.save(comment);
        //zapisanie mema
        memeRepository.save(meme);

    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByLogin(username);
    }

}
