package Cards;

import Cards.Animals.Bug;
import Cards.Animals.Wolf;
import Cards.Animals.Powers.SharpSpikes;
import org.junit.Test;
import static org.junit.Assert.*;

public class PowerTest {

    @Test
    public void testStinkyPower() {
        Bug bug = new Bug();
        assertTrue(bug.hasPower("Puant"));
        
        // Stinky power reduces attack by 1
        int modifiedAttack = bug.getPowers(0).get().onEnemyAttackCalculation(3);
        assertEquals(2, modifiedAttack); 
    }
    
    @Test
    public void testSharpSpikesPower() {
        Wolf wolf = new Wolf();
        SharpSpikes spikes = new SharpSpikes();
        
        int initialHp = wolf.get_healthPoint();
        
        spikes.onAttacked(wolf);
        
        assertEquals(initialHp - 1, wolf.get_healthPoint());
    }
    
    @Test
    public void testContactKillerPower() {
        Wolf defender = new Wolf();
        Cards.Animals.Powers.ContactKiller killer = new Cards.Animals.Powers.ContactKiller();
        
        killer.onDamageDealt(defender);
        
        assertTrue(defender.get_healthPoint() <= 0);
    }
    
    @Test
    public void testGrowthPower() {
        Cards.Animals.WolfPup pup = new Cards.Animals.WolfPup();
        Cards.Animals.Powers.Growth growth = new Cards.Animals.Powers.Growth();
        
        Cards.Card evolved = growth.onTurnEnd(pup);
        assertEquals(pup, evolved); 
        
        evolved = growth.onTurnEnd(pup); 
        assertEquals("Loup", evolved.get_nom()); 
    }
    
    @Test
    public void testMultiLivesPower() {
        gameplay.Board board = new gameplay.Board();
        Cards.Animals.Cat cat = new Cards.Animals.Cat(); 
        board.placeCard(cat, 0, gameplay.Side.PLAYER);
        gameplay.Player player = new gameplay.Player();
        
        Cards.Animals.Powers.Power multiLives = cat.getPowers(0).get();
        
        boolean survived = multiLives.onSacrificeEvent(player, board, 0);
        
        assertTrue(survived);
        assertFalse(cat.hasPower("Nombreuses Vies"));
    }
    
    @Test
    public void testSprinterPower() {
        gameplay.Board board = new gameplay.Board();
        Cards.Animals.Powers.Sprinter sprinter = new Cards.Animals.Powers.Sprinter();
        
        int newIndex = sprinter.onTurnEndMovement(board, 0, gameplay.Side.PLAYER);
        assertEquals(1, newIndex);
        
        newIndex = sprinter.onTurnEndMovement(board, 3, gameplay.Side.PLAYER);
        assertEquals(2, newIndex);
    }
}
