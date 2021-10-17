package pl.sdacademy.projekt3.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user; //String później zamienić na User
    private String text; //ok,
    private int memId;

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public int getMemId() {
        return memId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMemId(int memId) {
        this.memId = memId;
    }
}
