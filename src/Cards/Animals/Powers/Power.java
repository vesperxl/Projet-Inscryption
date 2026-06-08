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

    public int stinkyPower(int enemyAttack)
    {
        return enemyAttack;
    }

    public void contactKillerPower(Card cardDefender) {
    }

    public boolean multiLivesPower(Player player, Board board, int index)
    {
        player.addBloodStock();
        player.addBoneStock();

        board.removeCard(index, Side.PLAYER);
        return false;
    }

    public Card growthPower(Card currentCard)
    {
        return currentCard;
    }

    public void sharpSpikesPower(Card cardAttacker)
    {
    }

    public int sprinterPower(Board board, int currentIndex, Side side)
    {
        return currentIndex;
    }
}
