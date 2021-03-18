package home.sabapathy.gc.service;

import home.sabapathy.gc.model.Hero;
import home.sabapathy.gc.repository.HeroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(heroRepository);
    }

    /**
     * As a visitor, I can view all the heroes.
     * <p>
     * When I view all the heros
     * Then I can see names of all heros
     */
    @Test
    @DisplayName("Add Heroes")
    public void addHeroes() {
        when(heroRepository.save(any(Hero.class))).thenReturn(new Hero());

        heroService.add(new Hero());

        verify(heroRepository).save(any(Hero.class));
    }

    @Test
    @DisplayName("View Heroes")
    public void viewHeroes() {
        List<Hero> mockHeroesList = new ArrayList<>();

        when(heroRepository.findAll()).thenReturn(mockHeroesList);

        List<Hero> heroesList = heroService.view();

        verify(heroRepository).findAll();
        assertThat("", heroesList, is(equalTo(mockHeroesList)));
    }

    /**
     * As a visitor, I can see information about any individual hero so that I can see their stats.
     * <p>
     * Rule: Heroes have an image, real name, hero name, height, weight, special power, intelligence,
     * strength, power, speed, agility, description, and story.
     * <p>
     * Given I have the name of a hero
     * When I retreive the hero
     * Then I can view all the details for that hero
     */
    @Test
    @DisplayName("Show details of a Hero")
    public void heroDetails() {
        when(heroRepository.findByName(any(String.class))).thenReturn(Optional.of(new Hero()));
        Optional<Hero> hero = heroService.viewByName(new String());
        verify(heroRepository).findByName(any(String.class));
        assertThat("", hero.isPresent(), is(true));
    }

    /**
     * As a visitor, I can see information about any individual hero so that I can see their stats.
     * <p>
     * Rule: Heroes have an image, real name, hero name, height, weight, special power, intelligence,
     * strength, power, speed, agility, description, and story.
     * <p>
     * Given I have an incorrect hero name
     * When I retreive details for that hero
     * Then I receive a message that it doesn't exist
     */
    @Test
    @DisplayName("No Hero found to show details")
    public void nonExistentHeroDetails() {
        when(heroRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        Optional<Hero> hero = heroService.viewByName(new String());
        verify(heroRepository).findByName(any(String.class));
        assertThat("", hero.isPresent(), is(false));
    }
}
