package core;

import Card.Card;
import gameplay.Board;
import gameplay.Player;
import gameplay.ScoreManager;
import gameplay.Side;

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
        _board = new Board();
        _score = new ScoreManager(_player);

        generateInitialObstacle();
    }

    public void generateInitialObstacle(){


        Random rand = new Random();

        for (int i = 0; i < 4; i++) {

            if (rand.nextInt(100) < 10) {
                if (_board.getCard(i, Side.ENEMY).isEmpty()) {

                    _board.placeCard(Card.getRandomObstacleCard(), i, Side.ENEMY);
                }
            }

            if (rand.nextInt(100) < 10) {
                if (_board.getCard(i, Side.PLAYER).isEmpty()) {

                    _board.placeCard(Card.getRandomObstacleCard(), i, Side.PLAYER);
                }
            }
        }

    }

        public PlaceStatus placeCard(int indexHand, int indexBoard) {

            Optional<Card> cardOpt = _player.getHand().getCard(indexHand);
            if (cardOpt.isEmpty()) {
                return PlaceStatus.CARD_NOT_FOUND;
            }


            if (indexBoard < 0 || indexBoard > 3) {
                return PlaceStatus.INVALID_INDEX;
            }


            if (_board.getCard(indexBoard, Side.PLAYER).isPresent()) {
                return PlaceStatus.CELL_OCCUPIED;
            }

            Card card = cardOpt.get();


            if (_player.getBloodStock() < card.getBlood()) {
                return PlaceStatus.NOT_ENOUGH_BLOOD;
            }

            if (_player.getBoneStock() < card.getBone()) {
                return PlaceStatus.NOT_ENOUGH_BONES;
            }


            boolean success = _board.placeCard(card, indexBoard, Side.PLAYER);

            if (success) {

                _player.removeBloodStock(card.getBlood());
                _player.removeBoneStock(card.getBone());

                _player.getHand().retire(indexHand);
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
            Optional<Card> card = _board.getCard(index,Side.PLAYER);

            if(card.isPresent()){
                _player.addBloodStock();
                _board.removeCard(index,Side.PLAYER);
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



   public ScoreManager getScore(){
        return _score;
   }






}
