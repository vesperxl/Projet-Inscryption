package Cards.Animals.Powers;

import Cards.Card;

public class SharpSpikes extends Power{
    @Override
    public String getName() {
        return "Piques Pointues";
    }

    @Override
    public void sharpSpikesPower(Card cardAttacker) {
        cardAttacker.takeDamage(1);
    }
}
