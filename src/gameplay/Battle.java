package gameplay;

import Card.Card;


import java.util.Optional;
import java.util.Random;

public class Battle
{
    private Board _board;

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

    public static void basicAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card)
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

    public static void flyableAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card)
    {
        scoreManager.addPoint(attack, attacker);
    }

    private void attack(Side attacker, Side defender, ScoreManager score, Player player)
    {
        for (int i = 0; i <= 3; i++)
        {
            Optional<Card> card = _board.getCard(i, attacker);
            if (card.isPresent()){
                Optional<Card> enemyCard = _board.getCard(i, defender);
                card.get().attack(attacker, i, score, enemyCard);

                if(enemyCard.isPresent() && enemyCard.get().get_healthPoint() <= 0){
                    killCard(i,defender,player);
                }
            }
        }

    }


    public boolean killCard(int index, Side side, Player player)
    {
        Optional<Card> card = _board.getCard(index,side);

        if(card.isPresent()){

            _board.removeCard(index,side);

            if(side == Side.PLAYER){
                player.addBoneStock();
            }
            return true;
        }
        return false;
    }

}
