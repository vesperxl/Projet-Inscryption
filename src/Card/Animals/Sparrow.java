package Card.Animals;

import Card.Card;
import gameplay.Battle;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.lang.module.FindException;
import java.util.Optional;

public class Sparrow extends AnimalsCard{
    public Sparrow()
    {
        super("Sparrow", 2,1, 1, 0);
    }

    @Override
    public void attack(Player attacker, int index, ScoreManager scoreManager,  Optional<Card> card)
    {
        Battle.flyableAttack(attacker, index, this.getAttack(), scoreManager, card);
    }

    @Override
    public String displaySigil(){
        return "Volant";
    }

}


