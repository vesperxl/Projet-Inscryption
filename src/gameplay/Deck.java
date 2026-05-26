package gameplay;

import Card.Animals.Squirrel;
import Card.Card;

import java.util.*;

public class Deck {

    private Deque<Card> _cards;

    private Deck(){
        this._cards = new ArrayDeque<>();
    }

    public static Deck randomDeck(){
        Deck deck = new Deck();


        Random rdm = new Random();
        int nbEcureuil = rdm.nextInt(4) + 8;

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

    public Card draw() throws Exception{
        if(_cards.isEmpty()){
            throw new Exception("Plus de carte dans le deck");
        }
        return this._cards.pop();
    }

}
