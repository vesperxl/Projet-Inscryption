package Card.Animals;

import Card.Animals.Power.Growth;

public class WolfPup extends AnimalsCard
{
    public WolfPup()
    {
        super("WolfPup", 1,1, 1, 0);
        this.addPower(new Growth());
    }
}
