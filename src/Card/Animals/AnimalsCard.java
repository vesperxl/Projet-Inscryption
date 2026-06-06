package Card.Animals;

import Card.Card;
import gameplay.Battle;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.util.Optional;

public abstract class AnimalsCard extends Card
{
    private int _attack;
    private int _blood;
    private int _bone;

    public AnimalsCard(String name, int hp, int attack, int blood, int bone)
    {
        super(name, hp);
        _attack = attack;
        _blood = blood;
        _bone = bone;
    }

    @Override
    public void attack(Player attacker, int index, ScoreManager scoreManager,  Optional<Card> card)
    {
        Battle.basicAttack(attacker, index, _attack, scoreManager, card);
    }

    @Override
    public int getAttack()
    {
        return _attack;
    }

    @Override
    public String displayAttack(){
        return "Att: " + getAttack();
    }

}
