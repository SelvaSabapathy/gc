package home.sabapathy.gc.controller;

import home.sabapathy.gc.model.Hero;
import home.sabapathy.gc.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gc/v1")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public List<HeroDto> view() {
        List<Hero> heroes = heroService.view();

        List<HeroDto> heroDtos = heroes.stream().map(hero -> new HeroDto(hero.getName(), hero.getHeroName())).collect(Collectors.toList());
        return heroDtos;
    }

    @PostMapping("/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody HeroDto heroDto) {
        Hero hero = new Hero();
        hero.setName(heroDto.getName());
        hero.setHeroName(heroDto.getHeroName());
        heroService.add(hero);
    }
}
