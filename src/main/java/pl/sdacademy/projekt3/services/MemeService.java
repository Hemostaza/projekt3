package pl.sdacademy.projekt3.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.MemeRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class MemeService {
    private final MemeRepository memeRepository;

    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }
    public void save(Meme meme){
        memeRepository.save(meme);
    }
    public void save(Meme meme, MultipartFile file){
        try {
            //zmiana multipart file na byte i przypisanie go obrazkowi mema
            byte[] image = file.getBytes();
            meme.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        memeRepository.save(meme);
    }

    public List<Meme> findAll(){
        List<Meme> list = memeRepository.findAll();
        Collections.reverse(list); //odwrócenie listy od najnowszych do najstarszych
        return list;
    }

    public void deleteById(int id){
        memeRepository.deleteById(id);
    }

    public Meme findById(int id){
        return memeRepository.findById(id).orElseThrow(()->new NullPointerException("Nie ma mema o zadanym id"));
    }

    public List<Meme> findAllByUserId(int id){
        return memeRepository.findAllByUser_Id(id);
    }

    public List<Meme> findAllByCategory(String category){
        return memeRepository.findAllByCategory(category);
    }

}
