package gameplay;

import Cards.Card;


import java.util.ArrayList;
import java.util.Optional;

public class Battle
{

    public static void basicAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> cardDefenderOPT, Board board, ArrayList<AttackData> attackHistory)
    {
        String attackerName = board.getCard(index,attacker).get().get_nom();


        if(cardDefenderOPT.isPresent()){

            Card cardDefender = cardDefenderOPT.get();
            String targetName = cardDefender.get_nom();

            int attackDiff = attack - cardDefender.get_healthPoint();;

            boolean isLethal = attack >= cardDefender.get_healthPoint();
            int overkill = Math.max(0,attackDiff);

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

            attackHistory.add(new AttackData(attackerName,targetName,attack,isLethal,overkill));
        }
        else {
            scoreManager.addPoint(attack, attacker);

            attackHistory.add(new AttackData(attackerName,"la balance",attack,false,0));


        }

    }

    public static void flyableAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory)
    {
        String attackerName = board.getCard(index, attacker).get().get_nom();

        scoreManager.addPoint(attack, attacker);

        attackHistory.add(new AttackData(attackerName, "la balance (depuis les airs)", attack, false, 0));


    }

    public void attack(Side attacker, Side defender, ScoreManager score, Player defenderPlayer, Board board, ArrayList<AttackData> attackHistory)
    {
        for (int i = 0; i <= 3; i++)
        {
            Optional<Card> card = board.getCard(i, attacker);
            if (card.isPresent()){
                Optional<Card> enemyCard = board.getCard(i, defender);

                card.get().attack(attacker, i, score, enemyCard, board, attackHistory);

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
