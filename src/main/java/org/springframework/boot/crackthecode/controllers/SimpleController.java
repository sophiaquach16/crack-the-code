package org.springframework.boot.crackthecode.controllers;
import org.springframework.boot.crackthecode.data.GameDao;
import org.springframework.boot.crackthecode.data.RoundDao;
import org.springframework.boot.crackthecode.models.Game;
import org.springframework.boot.crackthecode.models.Guess;
import org.springframework.boot.crackthecode.models.Round;
import org.springframework.boot.crackthecode.service.CrackTheCodeServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleController {

    private final GameDao gameDao;
    private final RoundDao roundDao;
    private CrackTheCodeServiceLayer service;


    public SimpleController(GameDao gameDao, RoundDao roundDao, CrackTheCodeServiceLayer service) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int generateCode() {
        String answer = service.generateAnswer();
        return gameDao.newGame(answer).getId();
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guessing(@RequestBody Guess guess) {
        Game game = gameDao.getGame(guess.getGameId());
        String answer = game.getAnswer();
        String userGuess = guess.getGuess();
        String result = service.calculate(userGuess, answer);
        Round round = roundDao.newRound(guess, answer, guess.getGameId(), result);
        if (result.equals("e:4:p:0")) { game.setStatus("finished"); }
        gameDao.update(game);
        return round;
    }

    @GetMapping("/game")
     public List<Game> getAllGames() {
        return gameDao.getAll();
    }

    @GetMapping("game/{id}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
        Game result = gameDao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("rounds/{gameId}")
    public List<Round> getAllRounds(@PathVariable int gameId) {
        return roundDao.getAll(gameId);
    }


}
