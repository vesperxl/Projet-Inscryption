package Cards.Animals;

import Cards.Animals.Powers.Stinky;

public class Bug extends AnimalsCard
{
    public Bug()
    {
        super("Punaise",2,1, 0, 2);
        this.addPower(new Stinky());
    }
}
