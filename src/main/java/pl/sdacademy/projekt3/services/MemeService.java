package pl.sdacademy.projekt3.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.projekt3.entities.Meme;
import pl.sdacademy.projekt3.repositories.MemeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemeService {
    private final MemeRepository memeRepository;

    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }
    public void save(Meme meme){
        if(meme.getId()!=null){
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Error");
            throw illegalArgumentException;
        }
        memeRepository.save(meme);
    }

    public List<Meme> findAll(){
        return memeRepository.findAll();
    }

    public void deleteById(int id){
        memeRepository.deleteById(id);
    }

    public Optional<Meme> findById(int id){
        return memeRepository.findById(id);
    }

    public List<Meme> findAllByUserId(int id){
        return memeRepository.findAllByUser_Id(id);
    }
}
