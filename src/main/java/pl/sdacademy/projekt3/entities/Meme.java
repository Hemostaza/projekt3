package pl.sdacademy.projekt3.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    //relacja do kategorii
    private String category;
    //relacja do użytkownika dodającego mema
    private String user;
    //Później obrazek
    private String description;
    private int rating = 0;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getUser() {
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

    public void setUser(String user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
