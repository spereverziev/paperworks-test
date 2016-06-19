package com.payworks.test.web.rest;

import com.payworks.test.PayworksTestApplication;
import com.payworks.test.domain.HeroPower;
import com.payworks.test.repository.HeroPowerRepository;
import com.payworks.test.service.HeroPowerService;
import com.payworks.test.web.util.TestUtil;
import com.payworks.test.web.dto.HeroPowerDto;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PayworksTestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class HeroPowerResourceTest {

    private static final String CREATE_HEROPOWER_ENDPOINT = "/api/heropowers";
    public static final String DEFAULT_HEROPOWER_NAME = "name";
    public static final String DEFAULT_HEROPOWER_DESCRIPTION = "description";

    @Autowired
    private HeroPowerRepository heroPowerRepository;

    @Autowired
    private HeroPowerService heroPowerService;

    private MockMvc restMvc;

    @Before
    public void setup() {
        HeroPowerResource heroPowerResource = new HeroPowerResource();
        ReflectionTestUtils.setField(heroPowerResource, "heroPowerService", heroPowerService);

        this.restMvc = MockMvcBuilders.standaloneSetup(heroPowerResource).build();
    }

    @Test
    public void testCreateHeroPower() throws Exception {
        //given
        HeroPowerDto heroPowerDto = new HeroPowerDto();
        heroPowerDto.setName(DEFAULT_HEROPOWER_NAME);
        heroPowerDto.setDescription(DEFAULT_HEROPOWER_DESCRIPTION);

        //when
        restMvc.perform(
                post(CREATE_HEROPOWER_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(heroPowerDto)))
                .andExpect(status().isCreated());

        //then
        assertThat(heroPowerRepository.findAll()).hasSize(1);
        HeroPower actualPower = heroPowerRepository.findAll().get(0);
        assertThat(actualPower.getName()).isEqualTo(DEFAULT_HEROPOWER_NAME);
        assertThat(actualPower.getDescription()).isEqualTo(DEFAULT_HEROPOWER_DESCRIPTION);
    }

    @Test
    public void testCreateHeroPower_shouldReturnBadRequest_whenBlankName() throws Exception {
        //given
        HeroPowerDto heroPowerDto = new HeroPowerDto();
        heroPowerDto.setName("");
        heroPowerDto.setDescription(DEFAULT_HEROPOWER_DESCRIPTION);

        //when
        ResultActions resultActions = restMvc.perform(
                post(CREATE_HEROPOWER_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(heroPowerDto)));


        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(heroPowerRepository.findAll()).hasSize(0);
    }

    @Test
    public void testCreateHeroPower_shouldReturnBadRequest_whenBlankDescription() throws Exception {
        //given
        HeroPowerDto heroPowerDto = new HeroPowerDto();
        heroPowerDto.setName(DEFAULT_HEROPOWER_NAME);
        heroPowerDto.setDescription("");

        //when
        ResultActions resultActions = restMvc.perform(
                post(CREATE_HEROPOWER_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(heroPowerDto)));


        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(heroPowerRepository.findAll()).hasSize(0);
    }

    @Test
    public void testGetAll() throws Exception {
        //given
        HeroPowerDto heroPowerDto = new HeroPowerDto();
        heroPowerDto.setName(DEFAULT_HEROPOWER_NAME);
        heroPowerDto.setDescription(DEFAULT_HEROPOWER_DESCRIPTION);

        heroPowerService.addPower(heroPowerDto);
        heroPowerService.addPower(heroPowerDto);
        heroPowerService.addPower(heroPowerDto);

        assertThat(heroPowerRepository.findAll()).hasSize(3);

        //when
        ResultActions resultActions = restMvc.perform(
                get(CREATE_HEROPOWER_ENDPOINT)
                        .contentType(TestUtil.APPLICATION_JSON_UTF8));

        //then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$" , hasSize(3)));
    }
}