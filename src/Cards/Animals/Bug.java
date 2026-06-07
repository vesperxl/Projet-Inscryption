package Cards.Animals;

import Cards.Animals.Power.Stinky;

public class Bug extends AnimalsCard
{
    public Bug()
    {
        super("Bug",2,1, 0, 2);
        this.addPower(new Stinky());
    }
}
