package org.springframework.boot.crackthecode.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.crackthecode.models.Guess;
import org.springframework.boot.crackthecode.models.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundDatabaseDao implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round newRound(Guess guess, String answer, int gameId, String result) {
        Round round = new Round(guess.getGuess(), answer);
        round.setGameId(gameId);
        round.setResult(result);

        final String sql = "INSERT INTO round(guess, time, result, gameId) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getGuess());
            statement.setTimestamp(2, round.getTimestamp());
            statement.setString(3, round.getResult());
            statement.setInt(4, round.getGameId());
            return statement;

        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());
        return round;
    }

    @Override
    public List<Round> getAll(int gameId) {
        final String sql = "SELECT id, guess, time, result, gameId "
            + "FROM round WHERE gameId = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
//            System.out.println(rs.getInt("id"));
            round.setId(rs.getInt("id"));
            round.setGuess(rs.getString("guess"));
            round.setTimestamp(rs.getTimestamp("time"));
            round.setResult(rs.getString("result"));
            round.setGameId(rs.getInt("gameId"));
            return round;
        }
    }

}
