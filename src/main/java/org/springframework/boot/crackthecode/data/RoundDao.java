package org.springframework.boot.crackthecode.data;

import org.springframework.boot.crackthecode.models.Game;
import org.springframework.boot.crackthecode.models.Guess;
import org.springframework.boot.crackthecode.models.Round;

import java.util.List;

public interface RoundDao {

    public Round newRound(Guess guess, String answer, int gameId, String result);

    List<Round> getAll(int gameId);
}
