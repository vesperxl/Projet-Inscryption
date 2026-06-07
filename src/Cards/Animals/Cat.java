package Cards.Animals;

import Cards.Animals.Powers.MultiLives;

public class Cat extends AnimalsCard
{
    public Cat()
    {
        super("Cat",1,0, 3, 0);
        this.addPower(new MultiLives());
    }
}
