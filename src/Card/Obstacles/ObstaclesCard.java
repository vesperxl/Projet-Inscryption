package Card.Obstacles;

import Card.Card;
import gameplay.Battle;
import gameplay.Player;
import gameplay.ScoreManager;

public abstract class ObstaclesCard extends Card {


    public ObstaclesCard(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack(Player attacker, int index, ScoreManager scoreManager, Card card)
    {

    }
}
