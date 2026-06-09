package Cards.Animals.Powers;

import Cards.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.Side;

import java.util.Optional;

public class MultiLives extends Power{
    @Override
    public String getName() {
        return "Nombreuses Vies";
    }

    @Override
    public boolean onSacrificeEvent(Player player, Board board, int index)
    {
        Optional<Card> cardOpt = board.getCard(index, Side.PLAYER);
        if (cardOpt.isPresent()) {
            cardOpt.get().removePower(this);
        }
        return true;
    }
}

