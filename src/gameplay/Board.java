package gameplay;

import Card.Card;
import java.util.Optional;

public class Board {

    private Optional<Card>[] _enemyLine;
    private Optional<Card>[] _playerLine;
    private Optional<Card>[] _enemyIntentions;
    private Player _player;


    public Board(Player player){
        this._enemyLine = new Optional[4];
        this._playerLine = new Optional[4];
        this._enemyIntentions = new Optional[4];
        this._player = player;


        for(int i = 0; i < 4; i++){
            this._enemyLine[i] = Optional.empty();
            this._playerLine[i] = Optional.empty();
            this._enemyIntentions[i] = Optional.empty();
        }
    }

    public boolean placeEnemyIntention(Card card, int index){
        index--;

        if(index < 0 || index > 3){
            return false;
        }

        if(this._enemyIntentions[index].isPresent()){
            return false;
        }


        this._enemyIntentions[index] = Optional.of(card);

        return true;
    }

    public boolean placeCard(Card card, int index, Player j){
        index--;

        if(index < 0 || index > 3){
            return false;
        }

        if(j == _player)
        {
            if(this._playerLine[index].isPresent())
            {
                return false;
            }
            this._playerLine[index] = Optional.of(card);
            return true;
        }
        else
        {
            if(this._enemyLine[index].isPresent())
            {
                return false;
            }
            this._enemyLine[index] = Optional.of(card);
            return true;
        }
    }

    public Optional<Card> getCard(int index, Player player)
    {
        if (player == this._player){
            return this._playerLine[index];
        }
        else{
            return this._enemyLine[index];
        }
    }

    public void moveEnemyIntention(){
        for(int i = 0; i < 4; i++){

            if (this._enemyIntentions[i].isPresent() && this._enemyLine[i].isEmpty()) {


                this._enemyLine[i] = this._enemyIntentions[i];

                this._enemyIntentions[i] = Optional.empty();
            }
        }
    }

    public void removeCard(int index, Player player)
    {
        if (player == this._player){
            this._playerLine[index] = null;
        }
        else{
            this._enemyLine[index]= null;
        }
    }


    public Optional<Card>[] getPlayerLine(){
        return _playerLine.clone();
    }

    public Optional<Card>[] getEnemyLine(){
        return _enemyLine.clone();
    }

    public Optional<Card>[] getEnemyIntentionsLine(){
        return _enemyIntentions.clone();
    }
}