package gameplay;

import Cards.Animals.Powers.Power;
import Cards.Card;


import java.util.Optional;

public class Battle
{

    public static void basicAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> cardDefenderOPT, Board board)
    {
        if(cardDefenderOPT.isPresent()){

            Optional<Card> cardAttacker = board.getCard(index, attacker);
            Card cardDefender = cardDefenderOPT.get();

            boolean hasContactKiller = false;

            for (int i = 0; i < cardDefender.getSizePower(); i++)
            {
               attack = cardDefender.getPowers(i).stinkyPower(cardAttacker.get().getAttack());

            }

            int attackDiff = attack;
            int cardLife = cardDefender.get_healthPoint();
            attackDiff -= cardLife;


            if (attackDiff > 0){
                cardDefender.takeDamage(attack);

                if(attacker == Side.PLAYER && board.getCard(index,Side.INTENTION).isPresent()){

                    Card intentionCard = board.getCard(index,Side.INTENTION).get();
                    intentionCard.takeDamage(attackDiff);
                }

            }
            else {

                cardDefender.takeDamage(attack);
            }

            for (int i = 0; i < cardAttacker.get().getSizePower(); i++)
            {
                cardAttacker.get().getPowers(i).contactKillerPower(cardDefender);
            }
        }
        else
        {
            scoreManager.addPoint(attack, attacker);
        }
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
