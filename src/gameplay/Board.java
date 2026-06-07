package gameplay;

import Cards.Card;
import java.util.Optional;

public class Board {

    private Optional<Card>[] _enemyLine;
    private Optional<Card>[] _playerLine;
    private Optional<Card>[] _enemyIntentions;



    public Board(){
        this._enemyLine = new Optional[4];
        this._playerLine = new Optional[4];
        this._enemyIntentions = new Optional[4];

        for(int i = 0; i < 4; i++){
            this._enemyLine[i] = Optional.empty();
            this._playerLine[i] = Optional.empty();
            this._enemyIntentions[i] = Optional.empty();
        }
    }

    public boolean placeEnemyIntention(Card card, int index){

        if(index < 0 || index > 3){
            return false;
        }

        if(this._enemyIntentions[index].isPresent()){
            return false;
        }


        this._enemyIntentions[index] = Optional.of(card);

        return true;
    }

    public boolean placeCard(Card card, int index, Side side){

        if(index < 0 || index > 3){
            return false;
        }

        if(side == Side.PLAYER)
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

    public Optional<Card> getCard(int index, Side side)
    {
        if (side == Side.PLAYER){
            return this._playerLine[index];
        }
        else if(side == Side.ENEMY){
            return this._enemyLine[index];
        }else{
            return this._enemyIntentions[index];
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

    public void removeCard(int index, Side side)
    {
        if (side == Side.PLAYER){
            this._playerLine[index] = Optional.empty();
        }
        else{
            this._enemyLine[index]= Optional.empty();
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