package Card.Animals;

import Card.Card;

public abstract class AnimalsCard extends Card
{
    private int _Attack;

    public AnimalsCard(String name, int hp,int attack)
    {
        super(name, hp);
        _Attack = attack;
    }

    public int get_Attack(){
        return _Attack;
    }
}
