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

    public static void basicAttack(Player attacker, int index, int attack, ScoreManager scoreManager, Card card)
    {
        if(card != null){
            int attackDiff = attack;
            int cardLife = card.get_healthPoint();
            attackDiff -= cardLife;

            if (attackDiff > 0){
                card.takeDamage(attack);
                scoreManager.addPoint(attackDiff, attacker);
            }
            else{
                card.takeDamage(attack);
            }
        }
        else{
            scoreManager.addPoint(attack, attacker);
        }
    }

    public static void flyableAttack(Player attacker, int index, int attack, ScoreManager scoreManager, Card card)
    {
        scoreManager.addPoint(attack, attacker);
    }

    private void attack(Player attacker, Player defender)
    {
        for (int i = 0; i <= 3; i++)
        {
            Card card = board.getCard(i, attacker);
            if (card != null){
                Card enemyCard = board.getCard(i, defender);
                card.attack(attacker, i, scoreManager, enemyCard);
            }
        }
    }

}
