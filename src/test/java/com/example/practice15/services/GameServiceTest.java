package com.example.practice15.services;

import com.example.practice15.models.Game;
import com.example.practice15.repositories.GameRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GameServiceTest {
    @MockBean
    private GameRepository gamerepository;

    @Captor
    private ArgumentCaptor<Game> captor;
    private GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
    @Test
    public void addGame() {
        Game game = new Game();
        game.setName("Metro");
        game.setCreationDate(new Date(18,5,12));
        gameService.addGame(game);

        verify(gamerepository).save(captor.capture());
        Game captured = captor.getValue();
        Assertions.assertEquals(game.getName(), captured.getName());
        Assertions.assertEquals(game.getCreationDate(),captured.getCreationDate());
    }
    @Test
    public void findAllGames() {
        Game game = new Game();
        game.setName("Apex");
        Game game1 = new Game();
        game1.setName("PUBG");
        when(gamerepository.findAll()).thenReturn(List.of(game,game1));
        Assertions.assertEquals(2, gameService.findAllGames().size());
    }
}
