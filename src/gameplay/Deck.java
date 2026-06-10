package gameplay;

import Cards.Animals.Squirrel;
import Cards.Card;

import java.util.*;

public class Deck {

    private ArrayList<Card> _cards;

    private Deck(){
        this._cards = new ArrayList<>();
    }

    public static Deck initialDeck(Deck source)
    {
        Deck deck = new Deck();

        deck._cards.addAll(source._cards);

        return deck;

    }
    public static Deck randomDeck(){
        Deck deck = new Deck();


        Random rdm = new Random();
            int nbEcureuil = rdm.nextInt(4) + 9;

        for(int i = 0; i < nbEcureuil;i++){
            deck._cards.add(new Squirrel());
        }

        for(int i = 0; i < 15 - nbEcureuil; i++){
            deck._cards.add(Card.getRandomAnimalCard());
        }

        deck.shuffle();

        return deck;
    }


    public void shuffle(){


        Collections.shuffle(this._cards);

    }
    public void addCard(Card carte){
        this._cards.add(carte);
    }

    public Card draw(){

        return this._cards.remove(getSizeDeck() -1);
    }

    public int getSizeDeck(){
        return _cards.size();
    }

    public boolean isEmpty(){
        return this._cards.isEmpty();
    }


    public Optional<Card> getCard(int index) {
        if (index < 0 || index >= _cards.size()) {
            return Optional.empty();
        }

        return Optional.of(_cards.get(index));
    }


}
