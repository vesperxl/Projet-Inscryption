package gameplay;

import Cards.Animals.Powers.Power;
import Cards.Card;
import java.util.Optional;
import java.util.ArrayList;

public class Battle
{
    public static void basicAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> cardDefenderOPT, Board board, ArrayList<AttackData> attackHistory)
    {
        String attackerName = board.getCard(index, attacker).get().get_nom();

        if(cardDefenderOPT.isPresent()){
            Optional<Card> cardAttacker = board.getCard(index, attacker);
            Card cardDefender = cardDefenderOPT.get();

            int currentAttack = attack;
            for (int i = 0; i < cardDefender.getSizePower(); i++)
            {
                Optional<Power> powerOpt = cardDefender.getPowers(i);
                if (powerOpt.isPresent())
                {
                    currentAttack = cardDefender.getPowers(i).get().onEnemyAttackCalculation(currentAttack);
                }
            }

            String targetName = cardDefender.get_nom();
            int attackDiff = currentAttack - cardDefender.get_healthPoint();
            boolean isLethal = currentAttack >= cardDefender.get_healthPoint();
            int overkill = Math.max(0, attackDiff);

            cardDefender.takeDamage(currentAttack);

            if(attackDiff > 0 && attacker == Side.PLAYER && board.getCard(index, Side.INTENTION).isPresent()){
                Card intentionCard = board.getCard(index, Side.INTENTION).get();
                intentionCard.takeDamage(attackDiff);
            }

            for (int i = 0; i < cardAttacker.get().getSizePower(); i++) {
                Optional<Power> powerOpt = cardDefender.getPowers(i);
                if (powerOpt.isPresent())
                {
                    cardAttacker.get().getPowers(i).get().onDamageDealt(cardDefender);
                }
            }

            for (int i = 0; i < cardDefender.getSizePower(); i++)
            {
                Optional<Power> powerOpt = cardDefender.getPowers(i);
                if (powerOpt.isPresent()) {
                    cardDefender.getPowers(i).get().onAttacked(cardAttacker.get());
                }
            }

            attackHistory.add(new AttackData(attackerName, targetName, currentAttack, isLethal, overkill));
        }
        else {
            scoreManager.addPoint(attack, attacker);
            attackHistory.add(new AttackData(attackerName, "la balance", attack, false, 0));
        }
    }

    public static void flyableAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory)
    {
        String attackerName = board.getCard(index, attacker).get().get_nom();
        scoreManager.addPoint(attack, attacker);
        attackHistory.add(new AttackData(attackerName, "la balance (depuis les airs)", attack, false, 0));
    }

    public static void flyableAttack(Side attacker, int index, int attack, ScoreManager scoreManager, Optional<Card> card, Board board)
    {
        flyableAttack(attacker, index, attack, scoreManager, card, board, new ArrayList<>());
    }

    public void attack(Side attacker, Side defender, ScoreManager score, Player defenderPlayer, Board board, ArrayList<AttackData> attackHistory)
    {
        ArrayList<Card> actedCards = new ArrayList<>();

        for (int i = 0; i < 4; i++)
        {
            Optional<Card> cardOpt = board.getCard(i, attacker);

            if (cardOpt.isPresent() && !actedCards.contains(cardOpt.get())){

                Card card = cardOpt.get();
                actedCards.add(card);

                Optional<Card> enemyCard = board.getCard(i, defender);

                card.attack(attacker, i, score, enemyCard, board, attackHistory);

                if(enemyCard.isPresent() && enemyCard.get().get_healthPoint() <= 0)
                {
                    killCard(i, defender, defenderPlayer, board);
                }

                if(attacker == Side.PLAYER)
                {
                    Optional<Card> enemyIntention = board.getCard(i, Side.INTENTION);
                    if(enemyIntention.isPresent() && enemyIntention.get().get_healthPoint() <= 0)
                    {
                        killCard(i, Side.INTENTION, defenderPlayer, board);
                    }
                }

                for (int j = 0; j < card.getSizePower(); j++)
                {
                    int newIndex = card.getPowers(j).get().onTurnEndMovement(board, i, attacker);
                    if (newIndex != i)
                    {
                        board.placeCard(card, newIndex, attacker);
                        board.removeCard(i, attacker);
                        break;
                    }
                }
            }
        }
    }

    public boolean killCard(int index, Side side, Player defenderPlayer, Board board)
    {
        Optional<Card> card = board.getCard(index, side);

        if(card.isPresent()){
            board.removeCard(index, side);

            if(side == Side.PLAYER){
                defenderPlayer.addBoneStock();
            }
            return true;
        }
        return false;
    }
}