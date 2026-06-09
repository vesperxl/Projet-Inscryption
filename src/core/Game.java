package core;

import Cards.Animals.*;
import Cards.Attacker;
import Cards.Card;
import gameplay.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Game {

    private Board _board;
    private Player _player;
    private Player _ennemy;
    private ScoreManager _score;
    private boolean _hasDraw;
    private int _turnCounter;
    private Battle _battlePhase;
    private ArrayList<AttackData> _attackHistory = new ArrayList<>();

    public Game(Player player){
        _player = player;
        _ennemy = new Player();
        _board = new Board();
        _score = new ScoreManager();
        _turnCounter = 0;
        _battlePhase = new Battle();

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

        if(_hasDraw){
            return DrawStatus.ALREADY_DRAWN;
        }

        if(player.getDeck().isEmpty()){
            return DrawStatus.EMPTY_DECK;
        }



        player.draw();
        _hasDraw = true;

        return DrawStatus.SUCCESS;
   }

    public boolean sacrifice(int index){
        Optional<Card> card = _board.getCard(index,Side.PLAYER);
        if(card.isPresent()){
            return card.get().sacrifice(_player,_board,index);
        }
        return false;

    }


   public ScoreManager getScore(){
        return _score;
   }


   public void planOpponentNextTurn(){
        switch(this._turnCounter){
            case 1:
                _board.placeEnemyIntention(new Squirrel(), 1);
                break;
            case 2:
                _board.placeEnemyIntention(new Ermine(), 0);
                break;
            case 3:
                break;
            case 4:
                _board.placeEnemyIntention(new Cat(), 3);
                _board.placeEnemyIntention(new WolfPup(), 2);
                break;
            case 5:
                break;
            case 6:
                _board.placeEnemyIntention(new Sparrow(),0);
                break;
            case 7:
                _board.placeEnemyIntention(new Wolf(), 1);
                break;
            case 8:
                _board.placeEnemyIntention(new Squirrel(), 2);
                break;
            case 9:
                break;
            case 10 :
                _board.placeEnemyIntention(new Grizzly(), 0);
                break;
            default:
                _board.placeEnemyIntention(new Bug(), 3);

        }



   }


   public boolean isGameOver(){
        return this._score.isGameOver();
   }

   public boolean playerWin(){
        return this._score.playerVictory();
   }


   public int getTurnCounter(){
        return _turnCounter;
   }


   public void startNewTurn(){

        getPlayer().resetBlood();
        _hasDraw = false;
        _turnCounter++;
        applyGrowth();
   }

    public void applyGrowth()
    {
        for (int i = 0; i < 4; i++)
        {
            Optional<Card> optCard = _board.getCard(i, Side.PLAYER);
            if (optCard.isPresent())
            {
                Card current = optCard.get();
                for (int j = 0; j < current.getSizePower(); j++)
                {
                    Card evolved = current.getPowers(j).get().growthPower(current);
                    _board.removeCard(i, Side.PLAYER);
                    _board.placeCard(evolved, i, Side.PLAYER);

                    if (evolved != current)
                    {
                        _board.removeCard(i, Side.PLAYER);
                        _board.placeCard(evolved, i, Side.PLAYER);
                        break;
                    }
                }
            }
        }
    }

    public int caseTrad(String input){
        if (input == null || input.length() != 2){
            return -1;
        }

        if(input.charAt(0) != 'B'){
            return -1;
        }


        int index = input.charAt(1) - '0';
        index--;

        if (index >= 0 && index < 4) {
            return index;
        } else {
            return -1;
        }

    }

    public void executeCombatPhase(Side attacker, Side defender, Player defenderPlayer){
            _battlePhase.attack(attacker, defender, _score,defenderPlayer,_board, this._attackHistory);
    }

    public Player getEnemy(){
        return _ennemy;
    }

    public int sizeAttackHistory() { return this._attackHistory.size(); };

    public void clearAttackHistory(){
        this._attackHistory.clear();
    }

    public AttackData getAttackHistory(int index){
        return this._attackHistory.get(index);
    }

    public SacrificeStatus useSacrificeStone(int sourceBoardIndex, int targetBoardIndex) {

        if (sourceBoardIndex == targetBoardIndex) {
            return SacrificeStatus.SAME_CARD;
        }

        Optional<Card> sourceCardOpt = _board.getCard(sourceBoardIndex, Side.PLAYER);
        Optional<Card> targetCardOpt = _board.getCard(targetBoardIndex, Side.PLAYER);

        if (!sourceCardOpt.isPresent() || !targetCardOpt.isPresent()) {
            return SacrificeStatus.CARD_NOT_FOUND;
        }


        Card sourceCard = sourceCardOpt.get();
        Card targetCard = targetCardOpt.get();

        for (int i = 0; i < sourceCard.getSizePower(); i++)
        {
            if(!targetCard.addPower(sourceCard.getPowers(i).get()))
            {
                return SacrificeStatus.CANT_POWER_OBSTACLE;
            }
        }

            if (sourceCard.sacrifice(_player, _board, sourceBoardIndex))
            {
                return SacrificeStatus.SUCCESS;
            }
            else
            {
                for (int i = 0; i < sourceCard.getSizePower(); i++) {
                    targetCard.removePower(sourceCard.getPowers(i).get());
                }
                return SacrificeStatus.CANT_SACRIFICE_OBSTACLE;
            }


    }
}
