package game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreDAOImpl implements HighScoreDAO {

    private final String DB_URL = "jdbc:postgresql://localhost:5432/tankgame";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "password"; // change this

    @Override
    public void saveScore(HighScore highScore) {
        String sql = "INSERT INTO high_scores (name, score) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, highScore.name());
            stmt.setInt(2, highScore.score());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HighScore> getTopScores(int limit) {
        List<HighScore> scores = new ArrayList<>();
        String sql = "SELECT name, score FROM high_scores ORDER BY score DESC LIMIT ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scores.add(new HighScore(rs.getString("name"), rs.getInt("score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
