package Cards.Animals;

import Cards.Animals.Power.Growth;

public class WolfPup extends AnimalsCard
{
    public WolfPup()
    {
        super("WolfPup", 1,1, 1, 0);
        this.addPower(new Growth());
    }
}
