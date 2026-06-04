package gameplay;

import Card.Card;

import java.util.ArrayList;

public class Player {
    private Deck _deck;
    private Hand _hand;

    public Player(){
        this._deck = Deck.randomDeck();
        this._hand = new Hand(_deck);

    }

    public void draw() throws IllegalStateException{
        _hand.draw(_deck);
    }

    public Hand getHand(){
        return _hand;
    }


    }
