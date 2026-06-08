package Cards.Animals.Powers;

import Cards.Card;

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
}
