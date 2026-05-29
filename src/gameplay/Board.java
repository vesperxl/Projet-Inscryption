package gameplay;

import Card.Card;

public class Board {
    private Card[] _enemyLine;
    private Card[] _playerLine;

    public Board(){
        this._enemyLine = new Card[4];
        this._playerLine = new Card[4];
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
}
