package gameplay;

import Card.Card;
import Card.Obstacles.Fir;
import Card.Obstacles.Rock;


import java.sql.SQLClientInfoException;
import java.util.Optional;
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

    public static void basicAttack(Player attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card)
    {
        // verifie si l'optional nest pas null
        if(card.isPresent()){

            // sort la carte de l'optional
            Card actualCard = card.get();

            int attackDiff = attack;
            int cardLife = actualCard.get_healthPoint();
            attackDiff -= cardLife;


            if (attackDiff > 0){
                actualCard.takeDamage(attack);
                scoreManager.addPoint(attackDiff, attacker);
            }
            else {

                actualCard.takeDamage(attack);
            }
        }
        else {
            scoreManager.addPoint(attack, attacker);
        }

        //ancien code pour comparer

        /*
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
        }*/


    }

    public static void flyableAttack(Player attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card)
    {
        scoreManager.addPoint(attack, attacker);
    }

    private void attack(Player attacker, Player defender)
    {
        for (int i = 0; i <= 3; i++)
        {
            Optional<Card> card = board.getCard(i, attacker);
            if (card.isPresent()){
                Optional<Card> enemyCard = board.getCard(i, defender);
                card.get().attack(attacker, i, scoreManager, enemyCard);
            }
        }

        //Ton ancien code pour que tu compares
   /*
        for (int i = 0; i <= 3; i++)
        {
            Card card = board.getCard(i, attacker);
            if (card != null){
                Card enemyCard = board.getCard(i, defender);
                card.attack(attacker, i, scoreManager, enemyCard);
            }
        }

         */
    }

}
