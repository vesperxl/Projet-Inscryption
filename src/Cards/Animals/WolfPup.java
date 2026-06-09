package Cards.Animals;

import Cards.Animals.Powers.Growth;

public class WolfPup extends AnimalsCard
{
    public WolfPup()
    {
        super("Louveteau", 1,1, 1, 0);
        this.addPower(new Growth());
    }
}
