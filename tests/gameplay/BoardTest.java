package gameplay;

import Cards.Animals.Wolf;
import Cards.Obstacles.Fir;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testPlaceCard() {
        Board board = new Board();
        Wolf wolf = new Wolf();
        
        boolean success = board.placeCard(wolf, 0, Side.PLAYER);
        assertTrue(success);
        assertTrue(board.getCard(0, Side.PLAYER).isPresent());
        assertEquals(wolf, board.getCard(0, Side.PLAYER).get());
        
        boolean success2 = board.placeCard(new Fir(), 0, Side.PLAYER);
        assertFalse(success2);
    }
    
    @Test
    public void testPlaceEnemyIntention() {
        Board board = new Board();
        Wolf wolf = new Wolf();
        
        boolean success = board.placeEnemyIntention(wolf, 1);
        assertTrue(success);
        assertTrue(board.getCard(1, Side.INTENTION).isPresent());
        
        board.moveEnemyIntention();
        assertTrue(board.getCard(1, Side.ENEMY).isPresent());
        assertFalse(board.getCard(1, Side.INTENTION).isPresent());
    }
}
