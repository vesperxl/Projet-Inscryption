package gameplay;

import Cards.Animals.Squirrel;
import Cards.Card;

import java.util.*;

public class Deck {

    private Deque<Card> _cards;

    private Deck(){
        this._cards = new ArrayDeque<>();
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
            deck._cards.push(new Squirrel());
        }

        for(int i = 0; i < 15 - nbEcureuil; i++){
            deck._cards.push(Card.getRandomAnimalCard());
        }

        deck.shuffle();

        return deck;
    }


    public void shuffle(){
        List<Card> temp = new ArrayList<>(this._cards);
        Collections.shuffle(temp);
        this._cards.clear();
        this._cards.addAll(temp);

    }
    public void addCard(Card carte){
        this._cards.addLast(carte);
    }

    public Card draw(){

        return this._cards.pop();
    }

    public int getSizeDeck(){
        return _cards.size();
    }

    public boolean isEmpty(){
        return this._cards.isEmpty();
    }

    public Optional<Card> getCard(){

        return Optional.ofNullable(_cards.poll());

    }

    public ArrayList<Card> getCardsForDisplay() {

        return new ArrayList<>(_cards);
    }



}
