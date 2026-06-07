package Cards.Animals;

import Cards.Animals.Powers.SharpSpikes;

public class Porcupine extends AnimalsCard{
    public Porcupine() {
        super("Porcupine", 2, 1, 1, 0);
        this.addPower(new SharpSpikes());
    }
}
