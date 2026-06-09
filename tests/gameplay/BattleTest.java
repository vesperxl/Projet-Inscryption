package gameplay;

import Cards.Animals.Wolf;
import Cards.Animals.Squirrel;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Optional;
import java.util.ArrayList;

public class BattleTest {

    @Test
    public void testBasicAttackCardVsCard() {
        Board board = new Board();
        Wolf attacker = new Wolf();
        Squirrel defender = new Squirrel();
        
        board.placeCard(attacker, 0, Side.PLAYER);
        board.placeCard(defender, 0, Side.ENEMY);
        
        ScoreManager score = new ScoreManager();
        ArrayList<AttackData> history = new ArrayList<>();
        
        Battle.basicAttack(Side.PLAYER, 0, attacker.getAttack(), score, Optional.of(defender), board, history);
        
        assertEquals(1, history.size());
        assertEquals(3, history.get(0).getDamageDealt());
        assertTrue(history.get(0).isLethal());
        
        assertEquals(0, score.getCurrentScore());
    }
    
    @Test
    public void testBasicAttackDirect() {
        Board board = new Board();
        Wolf attacker = new Wolf();
        board.placeCard(attacker, 0, Side.PLAYER);
        
        ScoreManager score = new ScoreManager();
        ArrayList<AttackData> history = new ArrayList<>();
        
        Battle.basicAttack(Side.PLAYER, 0, attacker.getAttack(), score, Optional.empty(), board, history);
        
        assertEquals(3, score.getCurrentScore());
    }
    
    @Test
    public void testAttackAllCards() {
        Player player = new Player();
        Board board = new Board();
        ScoreManager score = new ScoreManager();
        Battle battle = new Battle();
        
        Wolf wolf1 = new Wolf();
        Wolf wolf2 = new Wolf();
        
        board.placeCard(wolf1, 0, Side.PLAYER);
        board.placeCard(wolf2, 2, Side.PLAYER);
        
        battle.attack(Side.PLAYER, Side.ENEMY, score, new Player(), board, new ArrayList<>());
        
        assertEquals(6, score.getCurrentScore());
    }
}
