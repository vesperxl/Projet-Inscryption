package Cards.Animals.Powers;

import Cards.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.Side;

public abstract class Power
{
    public String getName()
    {
        return "";
    }

    public int onEnemyAttackCalculation(int enemyAttack)
    {
        return enemyAttack;
    }

    public void onDamageDealt(Card cardDefender) {
    }

    public boolean onSacrificeEvent(Player player, Board board, int index)
    {
        return false;
    }

    public Card onTurnEnd(Card currentCard)
    {
        return currentCard;
    }

    public void onAttacked(Card cardAttacker)
    {
    }

    public int onTurnEndMovement(Board board, int currentIndex, Side side)
    {
        return currentIndex;
    }
}
