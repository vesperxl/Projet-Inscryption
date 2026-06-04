import Card.Card;
import gameplay.Board;
import gameplay.Player;

public class Game {

    private Board _board;
    private Player _player;
    //private ScoreManager _score;


    public Game(){
        _board = new Board(_player);
        _player = new Player();
        //_score = new ScoreManager();
    }

   public boolean placePlayerCard(Card card, int index){

        if(!_player.getHand().containsCard(card)){
            return false;
        }

        _player.getHand().retire(index);
        return _board.placePlayerCard(card,index);
   }

   public void sacrifice(Card card)
   {

   }


}
