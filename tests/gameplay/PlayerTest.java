package gameplay;

import Cards.Animals.Wolf;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testDrawCard() {
        Player player = new Player();
        int initialHandSize = player.getHand().getSize();
        int initialDeckSize = player.getDeck().getSizeDeck();
        
        player.draw();
        
        assertEquals(initialHandSize + 1, player.getHand().getSize());
        assertEquals(initialDeckSize - 1, player.getDeck().getSizeDeck());
    }
    
    @Test
    public void testAddCardToInitialDeck() {
        Player player = new Player();
        int sizeBefore = player.getInitialDeck().getSizeDeck();
        
        player.getInitialDeck().addCard(new Wolf());
        
        assertEquals(sizeBefore + 1, player.getInitialDeck().getSizeDeck());
        
        player.newGame();
        
        assertEquals(sizeBefore + 1 - 4, player.getDeck().getSizeDeck());
    }
}
