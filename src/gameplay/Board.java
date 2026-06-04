package gameplay;

import Card.Card;

public class Board {
    private Card[] _enemyLine;
    private Card[] _playerLine;
    private Card[] _enemyIntentions;
    private Player _player;




    public Board(Player player){
        this._enemyLine = new Card[4];
        this._playerLine = new Card[4];
        this._enemyIntentions = new Card[4];
        this._player = player;
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

    public Card getCard(int index, Player player)
    {
        if (player == this._player){
            return this._playerLine[index];
        }
        else{
            return this._enemyLine[index];
        }
    }
}
