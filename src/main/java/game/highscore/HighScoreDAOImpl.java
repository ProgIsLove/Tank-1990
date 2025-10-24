package game.highscore;

import game.database.DataAccessException;
import game.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HighScoreDAOImpl implements HighScoreDAO {

    @Override
    public void saveScore(HighScore highScore) {
        String sql = "INSERT INTO high_scores (name, score) VALUES (?, ?)";

        try (DatabaseConnection db = DatabaseConnection.getInstance();
             PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {

            stmt.setString(1, highScore.name());
            stmt.setInt(2, highScore.score());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error saving high score", e);
        }
    }

    @Override
    public List<HighScore> getTopScores(int limit) {
        List<HighScore> scores = new ArrayList<>();
        String sql = "SELECT name, score FROM high_scores ORDER BY score DESC LIMIT ?";

        try (DatabaseConnection db = DatabaseConnection.getInstance();
             PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scores.add(new HighScore(rs.getString("name"), rs.getInt("score")));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving top scores", e);
        }

        return scores;
    }
}
