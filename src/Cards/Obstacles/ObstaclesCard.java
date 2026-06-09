package Cards.Obstacles;

import Cards.Animals.Powers.Power;
import Cards.Card;
import gameplay.*;

import java.util.ArrayList;
import java.util.Optional;

public abstract class ObstaclesCard extends Card {


    public ObstaclesCard(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory)
    {

    }

    @Override
    public int getBone(){
        return 0;
    }

    @Override
    public int getBlood(){
        return 0;
    }

    @Override
    public boolean addPower(Power p)
    {
        return false;
    }
}
