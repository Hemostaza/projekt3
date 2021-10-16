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
    private int rating;

}
