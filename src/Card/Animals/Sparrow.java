package Card.Animals;

import Card.Card;
import gameplay.Battle;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.lang.module.FindException;

public class Sparrow extends AnimalsCard{
    public Sparrow()
    {
        super("Sparrow", 2,1, 1, 0);
    }

    @Override
    public void attack(Player attacker, int index, ScoreManager scoreManager,  Card card)
    {
        Battle.flyableAttack(attacker, index, this.getAttack(), scoreManager, card);
    }

    @Override
    public String displaySigil(){
        return "Volant";
    }

}


