package Cards.Animals;

import Cards.Card;
import gameplay.*;

import java.util.ArrayList;
import java.util.Optional;

public class Sparrow extends AnimalsCard{
    public Sparrow()
    {
        super("Moineau", 2,1, 1, 0);
    }

    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory)
    {
        Battle.flyableAttack(attacker, index, this.getAttack(), scoreManager, card, board, attackHistory);
    }

    @Override
    public String displaySigil(){
        return "Volant";
    }

}


