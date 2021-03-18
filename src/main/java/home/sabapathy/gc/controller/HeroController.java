package home.sabapathy.gc.controller;

import home.sabapathy.gc.model.Hero;
import home.sabapathy.gc.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

        List<HeroDto> heroDtos = heroes.stream()
                .map(hero -> new HeroDto(hero.getAnum(), hero.getName(), hero.getHeroName()))
                .collect(Collectors.toList());
        return heroDtos;
    }

    @PostMapping("/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody HeroDto heroDto) {
        Hero hero = new Hero();
        hero.setAnum(heroDto.getAnum());
        hero.setName(heroDto.getName());
        hero.setHeroName(heroDto.getHeroName());
        heroService.add(hero);
    }

    @GetMapping("heroes/{anum}")
    @ResponseBody
    public HeroDto view(@PathVariable Long anum) throws Exception {
        Optional<Hero> optionalHero = heroService.view(anum);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            HeroDto heroDto = new HeroDto(hero.getAnum(), hero.getName(), hero.getHeroName());
            return heroDto;
        }
        throw new Exception("The Hero " + anum + " doesn't exist");
    }
}
