package pl.sdacademy.projekt3.entities;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    private String text; //ok,
    private int memId;

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public int getMemId() {
        return memId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMemId(int memId) {
        this.memId = memId;
    }
}
