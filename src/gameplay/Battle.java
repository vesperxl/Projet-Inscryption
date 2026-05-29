package gameplay;

import Card.Card;
import Card.Obstacles.Fir;
import Card.Obstacles.Rock;


import java.sql.SQLClientInfoException;
import java.util.Random;

public class Battle
{
    private Board board;
    private ScoreManager scoreManager;

    private void initialize (){
        Random rand = new Random();

        for (int i = 0; i <= 3; i++){

            int chance = rand.nextInt(10);

            if (chance == 0)
            {
                Card.getRandomObstacleCard();
            }
        }
    }

    private void attack()
    {
        for (int i = 0; i <= 3; i++)
        {
            Card attackCard = board.getPlayerCard(i);

            if (attackCard != null)
            {

            }
        }
    }

}
