package Cards.Animals;

import Cards.Animals.Power.Sprinter;

public class Moose extends AnimalsCard{
    public Moose() {
        super("Moose", 4, 2, 2, 0);
        this.addPower(new Sprinter());
    }
}
