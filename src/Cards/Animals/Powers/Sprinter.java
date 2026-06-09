package Cards.Animals.Powers;

import gameplay.Board;
import gameplay.Side;

public class Sprinter extends Power{
    @Override
    public String getName() {
        return "Coureur";
    }

    @Override
    public int sprinterPower(Board board, int currentIndex, Side side)
    {
        if (currentIndex + 1 < 4 && board.getCard(currentIndex + 1, side).isEmpty())
        {
            return currentIndex + 1;
        }

        if (currentIndex - 1 >= 0 && board.getCard(currentIndex - 1, side).isEmpty()) {
            return currentIndex - 1;
        }

        return currentIndex;
    }
}
