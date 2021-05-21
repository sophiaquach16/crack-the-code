package org.springframework.boot.crackthecode.data;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.crackthecode.TestApplicationConfiguration;
import org.springframework.boot.crackthecode.models.Guess;
import org.springframework.boot.crackthecode.models.Round;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundDatabaseDaoTest {

    @Autowired
    GameDao gamedao;
    @Autowired
    RoundDao roundDao;

    @Test
    void newRound() {
        Guess guess = new Guess();
        guess.setGuess("1234");
        guess.setGameId(1);
        Round round = roundDao.newRound(guess,"1235",1,"e:3:p:0");
        assertEquals(round.getGuess(), guess.getGuess());
    }

    @Test
    void getAll() {
        int current_rows = roundDao.getAll(1).size();
        Guess guess = new Guess();
        guess.setGuess("1234");
        guess.setGameId(1);
        Round round = roundDao.newRound(guess,"1235",1,"e:3:p:0");
        Guess guess2 = new Guess();
        guess.setGuess("1237");
        guess.setGameId(1);
        Round round2 = roundDao.newRound(guess,"1235",1,"e:3:p:0");
        assertEquals(roundDao.getAll(1).size(),current_rows+2);
    }
}