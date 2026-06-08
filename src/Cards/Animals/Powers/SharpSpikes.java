package Cards.Animals.Powers;

import Cards.Card;

public class SharpSpikes extends Power{
    @Override
    public String getName() {
        return "Sharp Spikes";
    }

    @Override
    public void sharpSpikesPower(Card cardAttacker) {
        cardAttacker.takeDamage(1);
    }
}
