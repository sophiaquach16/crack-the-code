package org.springframework.boot.crackthecode.data;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.crackthecode.TestApplicationConfiguration;
import org.springframework.boot.crackthecode.models.Game;
import org.springframework.boot.crackthecode.models.Guess;
import org.springframework.boot.crackthecode.models.Round;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class GameDatabaseDaoTest {

    @Autowired
    GameDao gamedao;
    @Autowired
    RoundDao roundDao;

    @Test
    void newGame() {
        String answer = "1234";
        Game game = gamedao.newGame(answer);
        assertNotNull(game.getId());
        assertEquals(game.getAnswer(),answer);
    }

    @Test
    void getAll() {
        int currentNumberGames = gamedao.getAll().size();
        String answer = "1234";
        Game game = gamedao.newGame(answer);
        assertEquals(gamedao.getAll().size(), currentNumberGames+1);
    }

    @Test
    void findById() {
        int id = gamedao.newGame("9876").getId();
        assertEquals(gamedao.findById(id).getAnswer(), "---");
        assertEquals(gamedao.findById(id).getStatus(), "in progress");
    }

    @Test
    void getGame() {
        int id = gamedao.newGame("9876").getId();
        assertEquals(gamedao.getGame(id).getAnswer(), "9876");
    }

    @Test
    void update() {
        Game game = gamedao.newGame("9876");
        int id = game.getId();
        assertEquals(gamedao.findById(id).getStatus(), "in progress");
        gamedao.update(game);
        assertEquals(gamedao.findById(id).getStatus(), "finished");
    }
}