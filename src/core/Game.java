package core;

import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;

import java.util.Optional;
import java.util.Random;

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

        generateInitialObstacle();
    }

    public void generateInitialObstacle(){


        Random rand = new Random();

        for (int i = 0; i < 4; i++) {

            if (rand.nextInt(100) < 10) {
                if (_board.getCard(i, _ennemy).isEmpty()) {

                    _board.placeCard(Card.getRandomObstacleCard(), i, _ennemy);
                }
            }

            if (rand.nextInt(100) < 10) {
                if (_board.getCard(i, _player).isEmpty()) {

                    _board.placeCard(Card.getRandomObstacleCard(), i, _player);
                }
            }
        }

    }

        public PlaceStatus placeCard(Player currentPlayer, int indexHand, int indexBoard) {

            Optional<Card> cardOpt = currentPlayer.getHand().getCard(indexHand);
            if (cardOpt.isEmpty()) {
                return PlaceStatus.CARD_NOT_FOUND;
            }


            if (indexBoard < 0 || indexBoard > 3) {
                return PlaceStatus.INVALID_INDEX;
            }


            if (_board.getCard(indexBoard, currentPlayer).isPresent()) {
                return PlaceStatus.CELL_OCCUPIED;
            }

            Card card = cardOpt.get();


            if (currentPlayer.getBloodStock() < card.getBlood()) {
                return PlaceStatus.NOT_ENOUGH_BLOOD;
            }

            if (currentPlayer.getBoneStock() < card.getBone()) {
                return PlaceStatus.NOT_ENOUGH_BONES;
            }


            boolean success = _board.placeCard(card, indexBoard, currentPlayer);

            if (success) {

                currentPlayer.removeBloodStock(card.getBlood());
                currentPlayer.removeBoneStock(card.getBone());

                currentPlayer.getHand().retire(indexHand);
                return PlaceStatus.SUCCESS;
            }


            return PlaceStatus.CELL_OCCUPIED;
        }


   public Board getBoard(){
        return _board;
   }

   public Player getPlayer(){
        return _player;
   }



   public DrawStatus draw(Player player){

        if(hasDraw){
            return DrawStatus.ALREADY_DRAWN;
        }

        if(player.getDeck().isEmpty()){
            return DrawStatus.EMPTY_DECK;
        }



        player.draw();
        hasDraw = true;

        return DrawStatus.SUCCESS;
   }

       public boolean sacrifice(int index)
       {
            Optional<Card> card = _board.getCard(index,_player);

            if(card.isPresent()){
                _player.addBloodStock();
                _board.removeCard(index,_player);
               _player.addBoneStock();
                return true;
            }
            return false;



            /* ton code
           _player.addBloodStock();
           _board.getCard(index,_player);
           _board.removeCard(index, _player);
           */

       }

       public boolean killCard(int index)
       {
           Optional<Card> card = _board.getCard(index,_player);

           if(card.isPresent()){

               _board.removeCard(index,_player);
               _player.addBoneStock();
               return true;
           }
           return false;
       }

   public ScoreManager getScore(){
        return _score;
   }






}
