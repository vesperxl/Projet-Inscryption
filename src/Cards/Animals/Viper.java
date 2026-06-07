package Cards.Animals;

import Cards.Animals.Power.ContactKiller;

public class Viper extends AnimalsCard{
    public Viper() {
        super("Viper", 1, 1, 2, 0);
        this.addPower(new ContactKiller());
    }
}
