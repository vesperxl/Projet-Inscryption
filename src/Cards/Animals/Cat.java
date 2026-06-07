package Cards.Animals;

import Cards.Animals.Power.MultiLives;

public class Cat extends AnimalsCard
{
    public Cat()
    {
        super("Cat",1,0, 3, 0);
        this.addPower(new MultiLives());
    }
}
