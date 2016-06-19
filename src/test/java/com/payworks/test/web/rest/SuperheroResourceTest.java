package com.payworks.test.web.rest;

import com.payworks.test.PayworksTestApplication;
import com.payworks.test.domain.HeroPower;
import com.payworks.test.domain.Publisher;
import com.payworks.test.domain.Superhero;
import com.payworks.test.repository.SuperheroRepository;
import com.payworks.test.service.HeroPowerService;
import com.payworks.test.service.SuperheroService;
import com.payworks.test.web.util.TestSuperHeroDto;
import com.payworks.test.web.util.TestUtil;
import com.payworks.test.web.dto.HeroPowerDto;
import com.payworks.test.web.dto.SuperheroDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static com.payworks.test.web.rest.HeroPowerResourceTest.DEFAULT_HEROPOWER_DESCRIPTION;
import static com.payworks.test.web.rest.HeroPowerResourceTest.DEFAULT_HEROPOWER_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PayworksTestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class SuperheroResourceTest {

    public static final String DEFAULT_HERO_NAME = "name";
    public static final String DEFAULT_PSEUDONYM = "pseudo";
    private static final String CREATE_HERO_ENDPOINT = "/api/superheroes";
    public static final Publisher DEFAULT_PUBLISHER = Publisher.DC;
    public static final String DEFAULT_DATE_OF_FIRST_APPEARANCE_STRING = "1990-11-12";
    public static final LocalDate DEFAULT_DATE_OF_FIRST_APPEARANCE_LOCAL_DATE = LocalDate.of(1990, 11, 12);

    @Autowired
    private SuperheroService superheroService;

    @Autowired
    private SuperheroRepository superheroRepository;

    @Autowired
    private HeroPowerService heroPowerService;

    private List<Long> powers;
    private MockMvc restMvc;

    @Before
    public void setup() {
        SuperheroResource superheroResource = new SuperheroResource();
        ReflectionTestUtils.setField(superheroResource, "superheroService", superheroService);

        this.restMvc = MockMvcBuilders.standaloneSetup(superheroResource).build();

        HeroPowerDto heroPowerDto = new HeroPowerDto();
        heroPowerDto.setName(DEFAULT_HEROPOWER_NAME);
        heroPowerDto.setDescription(DEFAULT_HEROPOWER_DESCRIPTION);

        HeroPower power1 = heroPowerService.addPower(heroPowerDto);
        HeroPower power2 = heroPowerService.addPower(heroPowerDto);
        powers = new LinkedList<>();
        powers.add(power1.getId());
        powers.add(power2.getId());
    }

    @After
    public void tearDown() {
        superheroRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAddSuperhero_withoutAllies() throws Exception {
        //given
        TestSuperHeroDto superheroDto = new TestSuperHeroDto();
        superheroDto.setName(DEFAULT_HERO_NAME);
        superheroDto.setPseudonym(DEFAULT_PSEUDONYM);
        superheroDto.setPublisher(DEFAULT_PUBLISHER);
        superheroDto.setDateOfFirstAppearance(DEFAULT_DATE_OF_FIRST_APPEARANCE_STRING);
        superheroDto.setPowers(powers);

        assertThat(superheroRepository.findAll()).isEmpty();

        //when
        restMvc.perform(
                post(CREATE_HERO_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(superheroDto)))

                .andExpect(status().isCreated());

        //then
        assertThat(superheroRepository.findAll()).hasSize(1);

        Superhero actualSuperhero = superheroRepository.findAll().get(0);

        assertThat(actualSuperhero.getName()).isEqualTo(DEFAULT_HERO_NAME);
        assertThat(actualSuperhero.getPseudonym()).isEqualTo(DEFAULT_PSEUDONYM);
        assertThat(actualSuperhero.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(actualSuperhero.getDateOfFirstAppearance()).isEqualTo(DEFAULT_DATE_OF_FIRST_APPEARANCE_LOCAL_DATE);
        assertThat(actualSuperhero.getAllies()).isEmpty();
        assertThat(actualSuperhero.getPowers()).hasSize(2);


    }

    @Test
    @Transactional
    public void testAddSuperhero_withAllies() throws Exception {
        //given
        TestSuperHeroDto superheroDto = new TestSuperHeroDto();
        superheroDto.setName(DEFAULT_HERO_NAME);
        superheroDto.setPseudonym(DEFAULT_PSEUDONYM);
        superheroDto.setPublisher(DEFAULT_PUBLISHER);
        superheroDto.setDateOfFirstAppearance(DEFAULT_DATE_OF_FIRST_APPEARANCE_STRING);
        superheroDto.setPowers(powers);

        Superhero superhero1 = new Superhero();
        superhero1.setName(DEFAULT_HERO_NAME);
        superhero1.setPseudonym(DEFAULT_PSEUDONYM);
        superhero1.setPublisher(DEFAULT_PUBLISHER);
        superhero1.setDateOfFirstAppearance(DEFAULT_DATE_OF_FIRST_APPEARANCE_LOCAL_DATE);
        superheroRepository.save(superhero1);

        List<Long> ids = new LinkedList<>();
        ids.add(superhero1.getId());
        superheroDto.setAllies(ids);

        //when
        restMvc.perform(
                post(CREATE_HERO_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(superheroDto)))
                .andExpect(status().isCreated());

        //then
        assertThat(superheroRepository.findAll()).hasSize(2);

        Superhero superhero = superheroRepository.findAll().get(1);

        assertThat(superhero.getAllies()).hasSize(1);
    }


    @Test
    public void testGetSuperhero() throws Exception {
        //given
        SuperheroDto superheroDto = new SuperheroDto();
        superheroDto.setName(DEFAULT_HERO_NAME);
        superheroDto.setPseudonym(DEFAULT_PSEUDONYM);
        superheroDto.setPublisher(DEFAULT_PUBLISHER);
        superheroDto.setPowers(powers);

        Superhero superhero1 = superheroService.createSuperhero(superheroDto);

        List<Long> ids = new LinkedList<>();
        ids.add(superhero1.getId());
        superheroDto.setAllies(ids);

        Superhero superhero = superheroService.createSuperhero(superheroDto);

        //when

        ResultActions resultActions = restMvc.perform(
                get(CREATE_HERO_ENDPOINT + "/" + superhero.getId())
                        .contentType(TestUtil.APPLICATION_JSON_UTF8));

        //then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.name").value(DEFAULT_HERO_NAME));
        resultActions.andExpect(jsonPath("$.pseudonym").value(DEFAULT_PSEUDONYM));
        resultActions.andExpect(jsonPath("$.publisher").value(DEFAULT_PUBLISHER.toString()));
        resultActions.andExpect(jsonPath("$.powers", hasSize(2)));
        resultActions.andExpect(jsonPath("$.allies", hasSize(1)));
    }

    @Test
    public void testGetAllSuperheroes() throws Exception {
        //given
        SuperheroDto superheroDto = new SuperheroDto();
        superheroDto.setName(DEFAULT_HERO_NAME);
        superheroDto.setPseudonym(DEFAULT_PSEUDONYM);
        superheroDto.setPublisher(DEFAULT_PUBLISHER);
        superheroDto.setPowers(powers);

        superheroService.createSuperhero(superheroDto);
        superheroService.createSuperhero(superheroDto);
        superheroService.createSuperhero(superheroDto);

        //when
        ResultActions resultActions = restMvc.perform(
                get(CREATE_HERO_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8));

        //then
        resultActions.andExpect(jsonPath("$", hasSize(3)));
    }

}