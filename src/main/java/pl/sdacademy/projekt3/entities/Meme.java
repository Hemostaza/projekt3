package pl.sdacademy.projekt3.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    //relacja do kategorii
    private String category;
    //relacja do użytkownika dodającego mema
    //wiele memów od jednego użytkownika
    @ManyToOne
    private User user;
    //Później obrazek
    private String description;
    private int rating = 0;
    @ElementCollection
    private List<EmbededComment> comments = new ArrayList<>();

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<EmbededComment> getComments(){
        return comments;
    }

    public void setComments(EmbededComment comment) {
        this.comments.add(comment);
    }
}
