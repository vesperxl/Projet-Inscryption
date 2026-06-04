package Card.Animals;

import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

public interface Attacker
{
    public void attack(Player attacker, int index,ScoreManager scoreManager,  Card card);
}
