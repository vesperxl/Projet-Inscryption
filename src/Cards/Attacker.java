package Cards;

import gameplay.AttackData;
import gameplay.Board;
import gameplay.ScoreManager;
import gameplay.Side;

import java.util.ArrayList;
import java.util.Optional;

public interface Attacker
{
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory);
}
