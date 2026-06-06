package Card.Animals;

import Card.Card;
import gameplay.Battle;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.util.Optional;

public class Raven extends AnimalsCard{
    public Raven()
    {
        super("Raven", 3,2, 2, 0);

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
