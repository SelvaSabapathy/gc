package home.sabapathy.gc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitorTest {

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
     *
     * When I view all the heros
     * Then I can see names of all heros
     */
    @Test
    @DisplayName("Add Heroes")
    public void addHeroes() {
        Hero hero = new Hero(1L, "Chiranjeevi", "Bigger than Bachchan");
        when(heroRepository.save(any(Hero.class))).thenReturn(new Hero());

        heroService.add(hero);

        verify(heroRepository).save(any(Hero.class));
    }

    @Test
    @DisplayName("View Heroes")
    public void viewHeroes() {
        List<Hero> mockHeroesList = Arrays.asList(new Hero[]{
                new Hero(1L, "Chiranjeevi", "Bigger than Bachchan"),
                new Hero(1L, "Chiranjeevi", "Smaller than Bachchan")
        });

        when(heroRepository.findAll()).thenReturn(mockHeroesList);

        List<Hero> heroesList = heroService.view();

        verify(heroRepository).findAll();
        assertThat("", heroesList, is(equalTo(mockHeroesList)));
    }

    /**
     * As a visitor, I can see information about any individual hero so that I can see their stats.
     *
     * Rule: Heroes have an image, real name, hero name, height, weight, special power, intelligence,
     * strength, power, speed, agility, description, and story.
     *
     * Given I have the name of a hero
     * When I retreive the hero
     * Then I can view all the details for that hero
     */
    @Test
    @DisplayName("Show details of a Hero")
    public void heroDetails() {
        when(heroRepository.findByName(any(String.class))).thenReturn(new Hero());
        Hero hero1 = heroService.viewByName("Mark");
        verify(heroRepository).findByName(any(String.class));
        assertThat("",hero1,is(equalTo(new Hero())));

    }

    /**
     * As a visitor, I can see information about any individual hero so that I can see their stats.
     *
     * Rule: Heroes have an image, real name, hero name, height, weight, special power, intelligence,
     * strength, power, speed, agility, description, and story.
     *
     * Given I have an incorrect hero name
     * When I retreive details for that hero
     * Then I receive a message that it doesn't exist
     */
    @Test
    @DisplayName("No Hero found to show details")
    public void nonExistentHeroDetails() {
    }
}
