package Cards.Animals;

import Cards.Card;
import gameplay.*;

import java.util.ArrayList;
import java.util.Optional;

public class Raven extends AnimalsCard{
    public Raven()
    {
        super("Corbeau", 3,2, 2, 0);

    }

    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory)
    {
        Battle.flyableAttack(attacker, index, this.getAttack(), scoreManager, card, board,attackHistory);
    }

    @Override
    public String displaySigil(){
        return "Volant";
    }


}
