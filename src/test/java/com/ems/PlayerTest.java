package com.ems;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import com.ems.controller.PlayerController;
import com.ems.controller.RestEmployeeController;
import com.ems.entity.Player;
import com.ems.service.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(value = PlayerController.class)
public class PlayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;


    @Test
    public void savePlayerTest() throws Exception {
        Player mockPlayer = new Player();
        mockPlayer.setId(100);
        mockPlayer.setName("sumanth");
        mockPlayer.setTeam("india");
        mockPlayer.setDob(new Date());
        mockPlayer.setFourRuns(1000L);
        mockPlayer.setSixRuns(2000L);
        mockPlayer.setOtherRuns(3000L);



        String inputInJson = this.mapToJson(mockPlayer);

        String URI = "/api/v1/savePlayer";

        Mockito.when(playerService.savePlayer(Mockito.any(Player.class))).thenReturn(mockPlayer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
