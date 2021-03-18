package home.sabapathy.gc.service;

import home.sabapathy.gc.controller.HeroDto;
import home.sabapathy.gc.model.Hero;
import home.sabapathy.gc.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroService {

    @Autowired
    HeroRepository heroRepository;

    public void add(Hero hero) {
        heroRepository.save(hero);
    }

    public List<Hero> view() {
        return heroRepository.findAll();
    }

    public Optional<Hero> viewById(long l) {
        return heroRepository.findById(l);
    }

    public Optional<Hero> viewByName(String name) {
        return heroRepository.findByName(name);
    }
}
