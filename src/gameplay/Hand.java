package gameplay;

import Card.Card;

import java.util.ArrayList;
import java.util.Optional;

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

    public void draw(Deck deck) throws IllegalStateException{
        _handCards.add(deck.draw());

    }

    public void retire(int index){
        this._handCards.remove(index);
    }

    public boolean containsCard(Card card){
        return _handCards.contains(card);
    }

    public Optional<Card> getCard(int index) {

        if (index < 0 || index >= _handCards.size()) {
            return Optional.empty();
        }

        return Optional.of(this._handCards.get(index));
    }




}
