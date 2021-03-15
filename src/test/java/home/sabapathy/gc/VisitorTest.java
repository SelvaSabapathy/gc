package home.sabapathy.gc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VisitorTest {

    @Mock
    HeroRepository heroRepository;

    @InjectMocks
    HeroService heroService;

    /**
     * As a visitor, I can view all the heroes.
     *
     * When I view all the heros
     * Then I can see names of all heros
     */
    @Test
    public void addHeroes() {
        heroService.add(new Hero(1L, "C", "Chiranjeevi"));
        heroService.add(new Hero(2L, "N", "Nagarjun"));
    }

    @Test
    public void viewHeroes() {
        Hero h1 = new Hero(1L, "C", "Chiranjeevi");
        Hero h2 = new Hero(2L, "N", "Nagarjun");
        heroService.add(h1);
        heroService.add(h2);
        List<Hero> heroList = heroService.view();
        assertEquals(heroList.size(), 2);
        assertEquals(heroList, Arrays.asList(new Hero[]{h1, h2}));
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
    public void heroDetails() {
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
    public void nonExistentHeroDetails() {
    }
}
