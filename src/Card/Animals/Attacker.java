package Card.Animals;

import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.util.Optional;

public interface Attacker
{
    public void attack(Player attacker, int index,ScoreManager scoreManager,  Optional<Card> card);
}
