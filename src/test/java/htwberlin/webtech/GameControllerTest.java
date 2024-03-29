package htwberlin.webtech;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwberlin.webtech.game.CreateGameInput;
import htwberlin.webtech.game.Game;
import htwberlin.webtech.game.GameController;
import htwberlin.webtech.game.JoinGameInput;
import htwberlin.webtech.spieler.Spieler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameController gameController;

    @Test
    public void testCreateGame() throws Exception {

        Spieler spielErsteller = new Spieler("Player1");
        CreateGameInput createGameInput = new CreateGameInput();
        createGameInput.gameName = "TestGame";
        createGameInput.gameCreatorName = "GameCreatorName";
        Game mockGame = new Game("TestGame", Collections.singletonList(spielErsteller));
        Mockito.when(gameController.createGame(Mockito.any(CreateGameInput.class)))
                .thenReturn(mockGame);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(createGameInput);

        mockMvc.perform(MockMvcRequestBuilders.post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestGame"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.spieler[0].playerName").value("Player1"));
    }


    @Test
    public void testDeleteGame() throws Exception {

        Long gameId = 1L;
        Mockito.doNothing().when(gameController).deleteGame(gameId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/game/{id}", gameId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(gameController, Mockito.times(1)).deleteGame(gameId);
    }
}
