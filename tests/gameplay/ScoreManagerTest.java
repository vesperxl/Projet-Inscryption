package gameplay;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreManagerTest {

    @Test
    public void testAddPoint() {
        ScoreManager scoreManager = new ScoreManager();
        scoreManager.addPoint(3, Side.PLAYER);
        assertEquals(3, scoreManager.getCurrentScore());

        scoreManager.addPoint(2, Side.ENEMY);
        assertEquals(1, scoreManager.getCurrentScore());
    }

    @Test
    public void testPlayerVictory() {
        ScoreManager scoreManager = new ScoreManager();
        scoreManager.addPoint(5, Side.PLAYER);
        assertTrue(scoreManager.playerVictory());
        assertTrue(scoreManager.isGameOver());
    }

    @Test
    public void testEnemyVictory() {
        ScoreManager scoreManager = new ScoreManager();
        scoreManager.addPoint(5, Side.ENEMY);
        assertTrue(scoreManager.enemyVictory());
        assertTrue(scoreManager.isGameOver());
    }
}
