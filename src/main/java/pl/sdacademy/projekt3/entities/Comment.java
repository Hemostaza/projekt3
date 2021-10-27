package pl.sdacademy.projekt3.entities;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userr; //String później zamienić na User
    private String text; //ok,

    public String getUser() {
        return userr;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.userr = user;
    }

    public void setText(String text) {
        this.text = text;
    }
}
