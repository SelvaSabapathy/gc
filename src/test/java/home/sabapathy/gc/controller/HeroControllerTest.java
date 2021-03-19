package home.sabapathy.gc.controller;

import home.sabapathy.gc.model.Hero;
import home.sabapathy.gc.service.HeroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroControllerTest {

    @Mock
    private HeroService heroService;

    @InjectMocks
    private HeroController heroController;

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(heroService);
    }

    /**
     * As a visitor, I can view all the heroes.
     * <p>
     * When I view all the heros
     * Then I can see names of all heros
     */
    @Test
    @Order(1)
    @DisplayName("Add Heroes")
    public void addHeroes() {
        doNothing().when(heroService).add(any(Hero.class));

        heroController.add(new HeroDto());

        verify(heroService).add(any(Hero.class));

    }

    @Test
    @Order(2)
    @DisplayName("View Heroes")
    public void viewHeroes() {
        when(heroService.view()).thenReturn(new ArrayList<Hero>());

        heroController.view();

        verify(heroService).view();
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
    @Order(3)
    @DisplayName("Show details of a Hero")
    public void heroDetails() throws Exception {
        HeroDto expectedHeroDto = new HeroDto();
        when(heroService.viewByName(any(String.class))).thenReturn(Optional.of(new Hero()));

        HeroDto heroDto = heroController.view("");

        verify(heroService).viewByName("");
        assertThat("", heroDto, is(expectedHeroDto));
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
    @Order(4)
    @DisplayName("No Hero found to show details")
    public void nonExistentHeroDetails() throws Exception {
        when(heroService.viewByName(any(String.class))).thenReturn(Optional.empty());

        ResponseStatusException rse = assertThrows(ResponseStatusException.class, () -> heroController.view(""));

        verify(heroService).viewByName("");
        assertThat("", rse.getMessage(), is("404 NOT_FOUND \"No hero found\""));
    }
}
