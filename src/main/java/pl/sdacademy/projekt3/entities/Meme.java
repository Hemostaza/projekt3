package pl.sdacademy.projekt3.entities;

import javax.persistence.*;
import java.util.*;

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
    private String alternateText;
    //Tutaj obrazek
    @Lob
    private byte[] image;

    private int rating = 0;
    //jeden mem do wielu koemntarzy
    //wiele komenarzy do jednego mema
    @OneToMany(orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

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

    public String getAlternateText() {
        return alternateText;
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

    public void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Comment> getComments() {
        Collections.reverse(comments);
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
