package gameplay;

import Card.Card;

import java.util.ArrayList;

public class Player {
    private Deck _deck;
    private Hand _hand;
    private int _bloodStock;
    private int _boneStock;

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

    public void addBloodStock()
    {
        _bloodStock += 1;
    }

    public void removeBloodStock(int quantity)
    {
        _bloodStock -= quantity;
    }

    public int getBloodStock()
    {
        return  _bloodStock;
    }

    public void addBoneStock()
    {
        _boneStock += 1;
    }

    public void removeBoneStock(int quantity)
    {
        _boneStock -= quantity;
    }

    public int getBoneStock()
    {
        return  _boneStock;
    }
}
