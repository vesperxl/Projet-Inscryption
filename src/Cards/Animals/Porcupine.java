package Cards.Animals;

import Cards.Animals.Powers.SharpSpikes;

public class Porcupine extends AnimalsCard{
    public Porcupine() {
        super("Porc-Epic", 2, 1, 1, 0);
        this.addPower(new SharpSpikes());
    }
}
