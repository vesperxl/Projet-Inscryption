package Cards.Animals.Powers;

import Cards.Animals.Wolf;
import Cards.Card;

import java.util.Optional;

public class Growth extends Power
{

    private int _turnsOnBoard = 0;

    @Override
    public String getName() {
        return "Croissance";
    }

    @Override
    public Card onTurnEnd(Card currentCard) {
        this._turnsOnBoard++;
        if (this._turnsOnBoard >= 2)
        {
            Card evolved = new Wolf();

            for (int i = 0; i < currentCard.getSizePower(); i++)
            {
                Optional<Power> p = currentCard.getPowers(i);

                if (p.isPresent() && !p.get().getName().equals("Croissance")) {
                    evolved.addPower(p.get());
                }
            }

            return evolved;
        }
        return currentCard;
    }
}
