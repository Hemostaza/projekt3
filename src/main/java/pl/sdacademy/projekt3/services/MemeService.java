package pl.sdacademy.projekt3.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.MemeRepository;

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

    public List<Meme> findAll(){
        return memeRepository.findAll();
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
