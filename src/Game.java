import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

public class Game {

    private Board _board;
    private Player _player;
    private Player _ennemy;
    private ScoreManager _score;
    private boolean hasPlayed;


    public Game(){
        _board = new Board(_player);
        _player = new Player();
        _score = new ScoreManager();
    }

   public boolean placePlayerCard(Card card, int index){

        if(!_player.getHand().containsCard(card)){
            return false;
        }

        _player.getHand().retire(index);
        return _board.placePlayerCard(card,index);
   }

   public Board getPlateau(){
        return _board;
   }

   public Player getPlayer(){
        return _player;
   }

   public void draw(Player player) throws IllegalStateException{

        if(hasPlayed){
            throw new IllegalStateException("Vous avez déjà joué(e) ce tour !");
        }

        player.draw();
   }




   public void sacrifice(Card card)
   {

   }


}
