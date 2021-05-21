package org.springframework.boot.crackthecode.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.crackthecode.models.Game;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GameDatabaseDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game newGame(String answer) {
        Game game = new Game();
        game.setAnswer(answer);
        game.setStatus("in progress");

        final String sql = "INSERT INTO game(answer, status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public List<Game> getAll() {
//        final String sql = "SELECT * FROM game;";
        final String sql = "SELECT id, status, CASE " +
                "WHEN status='in progress' THEN '---' ELSE answer END as answer " +
                "FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        final String sql = "SELECT id, status, CASE " +
                "WHEN status='in progress' THEN '---' ELSE answer END as answer " +
                "FROM game WHERE id = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }


    @Override
    public Game getGame(int id) {
        final String sql = "SELECT id, status, answer " +
                "FROM game WHERE id = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public void update(Game game) {
        final String sql = "UPDATE game SET "
                + "answer = ?, "
                + "status = ? "
                + "WHERE id = ?;";

        jdbcTemplate.update(sql,
                game.getAnswer(),
                "finished",
                game.getId());

    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
//            System.out.println(rs.getInt("id"));
            game.setId(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getString("status"));
//            System.out.println(game.getAnswer());
            return game;
        }
    }
}

