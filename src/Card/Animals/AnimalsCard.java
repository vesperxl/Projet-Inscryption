package Card.Animals;

import Card.Card;

public abstract class AnimalsCard extends Card
{
    private int _attack;
    private int _blood;
    private int _bone;

    public AnimalsCard(String name, int hp,int attack, int blood, int bone)
    {
        super(name, hp);
        _attack = attack;
        _blood = blood;
        _bone = bone;
    }

    public int get_attack(){
        return _attack;
    }
}
