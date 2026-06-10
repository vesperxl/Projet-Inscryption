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
    public void testAjoutNouvellesCartesPiocheFinDeuxiemePartie() {
        // Test de "l'ajout de nouvelles cartes dans la pioche à la fin de la deuxième partie"
        Player player = new Player();
        
        // On simule la fin de la deuxième partie
        player.newGame(); // Début partie 1
        player.newGame(); // Début partie 2
        
        int sizeBefore = player.getInitialDeck().getSizeDeck();
        
        // Ajout d'une carte à la pioche initiale comme dans GameDisplay.cardChoice()
        player.getInitialDeck().addCard(new Wolf());
        
        assertEquals(sizeBefore + 1, player.getInitialDeck().getSizeDeck());
        
        // Début de la partie 3, le joueur pioche sa main (4 cartes)
        player.newGame();
        
        assertEquals(sizeBefore + 1 - 4, player.getDeck().getSizeDeck());
    }
}
