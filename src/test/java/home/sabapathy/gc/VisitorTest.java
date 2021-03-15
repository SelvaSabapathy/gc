package home.sabapathy.gc;

import org.junit.jupiter.api.Test;

public class VisitorTest {

    /**
     * As a visitor, I can view all the heroes.
     *
     * When I view all the heros
     * Then I can see names of all heros
     */
    @Test
    public void viewHeroes() {
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
