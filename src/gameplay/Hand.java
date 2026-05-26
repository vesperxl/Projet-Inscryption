package gameplay;

import Card.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> _cardHand;
    private Deck _pioche;

    public Hand(){
        _cardHand = new ArrayList<>();
        _pioche = Deck.randomDeck();
        try{
            for(int i = 0;i < 3;i++){
                _cardHand.add(_pioche.piocher());
            }
        }
        catch(Exception e){
            System.out.println("Dans le constructeur : " +  e.getMessage());
        }

    }

    public void piocher(){
        try{
            _cardHand.add(_pioche.piocher());
        }catch(Exception e){
            System.out.println("Dans pioche() : " + e.getMessage());
        }
    }


}
