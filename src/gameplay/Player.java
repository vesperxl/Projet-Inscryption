package gameplay;

import Card.Card;

import java.util.ArrayList;

public class Player {
    private Deck _deck;
    private Hand _hand;
    private ArrayList<Card>  _board;

    public Player(boolean enemy){
        this._deck = Deck.randomDeck();
        this._hand = new Hand(_deck);
        this._enemy = enemy;
    }

    public void draw(){
        _hand.draw(_deck);
    }

}
