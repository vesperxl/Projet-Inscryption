package Card.Animals;

import Card.Card;
import gameplay.*;

import java.lang.module.FindException;
import java.util.Optional;

public class Sparrow extends AnimalsCard{
    public Sparrow()
    {
        super("Sparrow", 2,1, 1, 0);
    }

    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board)
    {
        Battle.flyableAttack(attacker, index, this.getAttack(), scoreManager, card);
    }

    @Override
    public String displaySigil(){
        return "Volant";
    }

}


