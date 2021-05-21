package org.springframework.boot.crackthecode.models;

public class Guess {

    private String guess;
    private int gameId;

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuess() {
        return guess;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public String calculateGuess() {
        return "";
    }


}
