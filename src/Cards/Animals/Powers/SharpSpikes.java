package Cards.Animals.Powers;

import Cards.Card;

public class SharpSpikes extends Power{
    @Override
    public String getName() {
        return "Piq. Point.";
    }

    @Override
    public void onAttacked(Card cardAttacker) {
        cardAttacker.takeDamage(1);
    }
}
