package org.springframework.boot.crackthecode.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Round {

    private int id;
    private String guess;
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private String result;
    private int gameId;

    public Round() {
    }

    public Round(String guess, String answer) {
        this.guess = guess;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setId(int id) { this.id = id; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public int getId() { return id; }

    public int getGameId() { return gameId; }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGuess() {
        return guess;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getResult() {
        return result;
    }

}
