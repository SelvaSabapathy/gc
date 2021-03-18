package home.sabapathy.gc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class HeroControllerTest {
    private static final String baseURL = "/gc/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * As a visitor, I can view all the heroes.
     *
     * When I view all the heros
     * Then I can see names of all heros
     */
    @Test
    @Order(1)
    @DisplayName("Add Heroes")
    public void addHeroes() throws Exception {
        HeroDto heroDto = new HeroDto("Chiranjeevi", "The Megastar");

        mockMvc.perform(post(baseURL + "/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroDto))
        ).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    @DisplayName("View Heroes")
    public void viewHeroes() throws Exception {
        HeroDto heroDto = new HeroDto("Chiranjeevi", "The Megastar");
        List<HeroDto> heroDtoList = Arrays.asList(new HeroDto[] {heroDto});

        mockMvc.perform(post(baseURL + "/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroDto))
        ).andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(get(baseURL + "/heroes")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andReturn();

        String heroos = mvcResult.getResponse().getContentAsString();
        assertThat("", objectMapper.readValue(heroos, new TypeReference<ArrayList<HeroDto>>() {}), is(heroDtoList));
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
    @Order(3)
    @DisplayName("Show details of a Hero")
    public void heroDetails() throws Exception {
        HeroDto expectedHeroDto = new HeroDto("Rajini", "The Superstar");
        mockMvc.perform(post(baseURL + "/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedHeroDto))
        ).andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(get(baseURL + "/heroes/Rajini").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String heroString = mvcResult.getResponse().getContentAsString();
        assertThat("", heroString, is(notNullValue()));
        assertThat("", heroString, is(not(emptyString())));

        HeroDto heroDto = objectMapper.readValue(heroString, HeroDto.class);
        assertThat("", heroDto, is(expectedHeroDto));
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
    @Order(4)
    @DisplayName("No Hero found to show details")
    public void nonExistentHeroDetails() {
    }
}
