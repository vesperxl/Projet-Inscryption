package gameplay;

import Card.Card;

public class Board {
    private Card[] _enemyLine;
    private Card[] _playerLine;
    private Card[] _enemyIntentions;




    public Board(){
        this._enemyLine = new Card[4];
        this._playerLine = new Card[4];
        this._enemyIntentions = new Card[4];
    }

    public boolean placeEnemyIntention(Card card, int index){
        index--;


        if(index < 0 || index > 3){
            return false;
        }

        if(this._enemyIntentions[index] != null){
            return false;
        }

        this._enemyIntentions[index] = card;

        return true;

    }

    public boolean placePlayerCard(Card card, int index){
        index--;

        if(index < 0 || index > 3){
            return false;
        }

        if(this._playerLine[index] != null){
            return false;
        }

        this._playerLine[index] = card;

        return true;
    }

    public boolean placeEnemyCard(Card card, int index){
        index--;

        if(index < 0 || index > 3){
            return false;
        }

        if(this._enemyLine[index] != null){
            return false;
        }

        this._enemyLine[index] = card;

        return true;
    }

    public Card getPlayerCard(int index)
    {
        return this._playerLine[index];
    }

    public Card getEnemiCard(int index)
    {
        return this._enemyLine[index];
    }

    public void moveEnemyIntention(){
        for(int i = 0;i < 4; i++){
            if (this._enemyIntentions[i] != null && this._enemyLine[i] == null) {

                this._enemyLine[i] = this._enemyIntentions[i];

                this._enemyLine[i] = null;
            }
        }
    }
}
