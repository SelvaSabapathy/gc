package home.sabapathy.gc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    @Autowired
    HeroRepository heroRepository;

    public void add(Hero hero) {
        heroRepository.save(hero);
    }

    public List<Hero> view() {
        return heroRepository.findAll();
    }
}
