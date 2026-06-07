package Card.Animals;

import Card.Animals.Power.MultiLives;
import Card.Animals.Power.Sprinter;

public class Cat extends AnimalsCard
{
    public Cat()
    {
        super("Cat",1,0, 3, 0);
        this.addPower(new MultiLives());
    }
}
