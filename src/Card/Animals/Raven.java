package Card.Animals;

import Card.Card;
import gameplay.*;

import java.util.Optional;

public class Raven extends AnimalsCard{
    public Raven()
    {
        super("Raven", 3,2, 2, 0);

    }

    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager,  Optional<Card> card)
    {
        Battle.flyableAttack(attacker, index, this.getAttack(), scoreManager, card);
    }

    @Override
    public String displaySigil(){
        return "Volant";
    }


}
