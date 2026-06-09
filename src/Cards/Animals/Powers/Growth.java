package Cards.Animals.Powers;

import Cards.Animals.Wolf;
import Cards.Card;

public class Growth extends Power
{

    private int _turnsOnBoard = 0;

    @Override
    public String getName() {
        return "Croissance";
    }

    @Override
    public Card growthPower(Card currentCard) {
        this._turnsOnBoard++;
        if (this._turnsOnBoard >= 2) {
            return new Wolf();
        }
        return currentCard;
    }
}
