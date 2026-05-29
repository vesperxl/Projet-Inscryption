package gameplay;

import Card.Obstacles.Fir;
import Card.Obstacles.Rock;

import java.util.Random;

public class Battle
{
    private Board board;
    private void initialize (){
        Random rand = new Random();

        for (int i = 0; i <= 3; i++){

            int chance = rand.nextInt(10);

            if (chance == 0)
            {
                chance = rand.nextInt(2);
                if (chance == 0)
                {
                    board.placePlayerCard(new Rock(), i);
                }
                else
                {
                    board.placePlayerCard(new Fir(), i);
                }
            }
        }
    }

    private void attack()
    {

    }

}
