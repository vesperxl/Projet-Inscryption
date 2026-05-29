package Card.Animals;

import Card.Card;

public abstract class FlyableCard extends Card {
    private int _attack;
    private int _blood;
    private int _bone;

    public FlyableCard(String name, int hp,int attack, int blood, int bone)
    {
        super(name, hp);
        _attack = attack;
        _blood = blood;
        _bone = bone;
    }

    // DEEEEMMMAAQANDDDER AU PROF COMMENT RLEIER FLYABLE CARD ANIMAL CARD ET OBSTACLE CARD A CARD CAR DOUBLON FLYABLE CARD ANIMALS CARD C GRAVE ?
}
