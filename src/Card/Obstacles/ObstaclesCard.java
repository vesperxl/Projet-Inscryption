package Card.Obstacles;

import Card.Card;
import gameplay.Battle;
import gameplay.Player;
import gameplay.ScoreManager;
import gameplay.Side;

import java.util.Optional;

public abstract class ObstaclesCard extends Card {


    public ObstaclesCard(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card)
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
}
