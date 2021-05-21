package org.springframework.boot.crackthecode.data;

import org.springframework.boot.crackthecode.models.Game;

import java.util.List;

public interface GameDao {

    Game newGame(String answer);

    List<Game> getAll();

    Game findById(int id);

    Game getGame(int id);

    void update(Game game);
}
