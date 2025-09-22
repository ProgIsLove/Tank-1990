package game;

import java.util.List;

public interface HighScoreDAO {

    void saveScore(HighScore highScore);
    List<HighScore> getTopScores(int limit);
}
