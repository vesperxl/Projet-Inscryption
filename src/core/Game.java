package core;

import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.util.Optional;

public class Game {

    private Board _board;
    private Player _player;
    private Player _ennemy;
    private ScoreManager _score;
    private boolean hasDraw;


    public Game(){
        _player = new Player();
        _ennemy = new Player();
        _board = new Board(_player);
        _score = new ScoreManager(_player);
    }

    public boolean placeCard(Player currentPlayer, int indexHand, int indexBoard) {

        Optional<Card> card = currentPlayer.getHand().getCard(indexHand);

        if (card.isEmpty()){
            return false;
        }

        boolean success = _board.placeCard(card.get(), indexBoard, currentPlayer);

        if (success) {
            currentPlayer.getHand().retire(indexHand);
            return true;
        }

        return false;
    }

   public Board getPlateau(){
        return _board;
   }

   public Player getPlayer(){
        return _player;
   }



   public void draw(Player player) throws IllegalStateException{

        if(hasDraw){
            throw new IllegalStateException("Vous avez déjà joué(e) ce tour !");
        }

        player.draw();
        hasDraw = false;
   }

   public void sacrifice(int index)
   {
       _player.addBloodStock();
       _board.getCard(index,_player);
       _board.removeCard(index, _player);
   }

   public void killCard(int index)
   {
       _player.addBoneStock();
       _board.getCard(index,_player);
       _board.removeCard(index, _player);
   }




}
