package gameplay;

import Card.Card;


import java.util.Optional;
import java.util.Random;

public class Battle
{

    public static void basicAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card, Board board)
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

                if(attacker == Side.PLAYER && board.getCard(index,Side.INTENTION).isPresent()){

                    Card intentionCard = board.getCard(index,Side.INTENTION).get();
                    intentionCard.takeDamage(attackDiff);
                }

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

    public void attack(Side attacker, Side defender, ScoreManager score, Player defenderPlayer, Board board)
    {
        for (int i = 0; i <= 3; i++)
        {
            Optional<Card> card = board.getCard(i, attacker);
            if (card.isPresent()){
                Optional<Card> enemyCard = board.getCard(i, defender);

                card.get().attack(attacker, i, score, enemyCard, board);

                if(enemyCard.isPresent() && enemyCard.get().get_healthPoint() <= 0){
                    killCard(i,defender,defenderPlayer,board);
                }

                if(attacker == Side.PLAYER){
                    Optional<Card> enemyIntention = board.getCard(i, Side.INTENTION);
                    if(enemyIntention.isPresent() && enemyIntention.get().get_healthPoint() <= 0){
                        killCard(i, Side.INTENTION, defenderPlayer,board);
                    }

                }
            }
        }

    }


    public boolean killCard(int index, Side side, Player defenderPlayer, Board board)
    {
        Optional<Card> card = board.getCard(index,side);

        if(card.isPresent()){

            board.removeCard(index,side);

            if(side == Side.PLAYER){
                defenderPlayer.addBoneStock();
            }
            return true;
        }
        return false;
    }

}
