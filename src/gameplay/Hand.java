package gameplay;

import Card.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> _handCards;

    public Hand(Deck deck){
       this._handCards = new ArrayList<>();
            for(int i = 0; i < 4;i++)
            {
                try {
                    this._handCards.add(deck.draw());
                }catch(Exception e){
                        System.out.println("Dans le constructeur : " +  e.getMessage());
                }
            }
        }

    public void draw(Deck deck){
        try{
            _handCards.add(deck.draw());
        }catch(Exception e){
            System.out.println("Dans pioche() : " + e.getMessage());
        }
    }

    public void retire(int index){
        this._handCards.remove(index);
    }


}
