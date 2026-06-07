package Card.Animals;

import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;
import gameplay.Side;

import java.util.Optional;

public interface Attacker
{
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board);
}
